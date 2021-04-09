package app.wishlisted.android.data.src.repository.game

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.flatMap
import androidx.paging.map
import androidx.room.withTransaction
import app.wishlisted.android.data.src.api.game.GameApi
import app.wishlisted.android.data.src.db.AppDatabase
import app.wishlisted.android.data.src.db.dao.GameDao
import app.wishlisted.android.data.src.db.dao.StatusDao
import app.wishlisted.android.data.src.model.GameDTO
import app.wishlisted.android.data.src.model.StatusDTO
import app.wishlisted.android.data.src.model.StatusGameCrossRef
import app.wishlisted.android.data.src.model.StatusWithGames
import app.wishlisted.android.data.src.model.toDomainModel
import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameApi: GameApi,
    private val gameDao: GameDao,
    private val statusDao: StatusDao,
    private val appDatabase: AppDatabase
) : GameRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun fetchGameDeals(): Flow<PagingData<Game>> {
        val statusId = statusDao.fetchAll().firstOrNull { it.name == "Deals" }?.statusId ?: 0
//        val mediator = PageKeyedRemoteMediator(
//            database = appDatabase,
//            api = gameApi,
//            dao = gameDao,
//            fetchIds = { gameApi.fetchDeal(it) },
//            statusId = statusId
//        )

        return Pager(
            config = PagingConfig(20, prefetchDistance = 10),
            pagingSourceFactory = {
                DealsDataSource(gameApi) {
                    gameApi.fetchDeal(it)
                }
            }
        ).flow
    }

    override suspend fun fetchStatus(): Result<Unit> {
        return try {
            val status = gameApi.fetchStatuses().mapIndexed { index, s ->
                StatusDTO(statusId = index, name = s)
            }

            statusDao.insertAll(status)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    class DealsDataSource(
        private val api: GameApi,
        private val fetchIds: suspend (skipItems: Int) -> List<String>
    ) : PagingSource<Int, Game>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
            return try {
                val ids = fetchIds(params.key ?: 0)
                val result = api
                    .fetchGames(ids, "us", "en")
                    .map { gameDto -> gameDto.toDomainModel() }

                LoadResult.Page(
                    data = result,
                    prevKey = params.key,
                    nextKey = params.key?.plus(params.loadSize) ?: params.loadSize * 2
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Game>): Int {
            return 0
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    class PageKeyedRemoteMediator(
        private val database: AppDatabase,
        private val api: GameApi,
        private val dao: GameDao,
        private val fetchIds: suspend (skipItems: Int) -> List<String>,
        private val statusId: Int
    ) : RemoteMediator<Int, StatusWithGames>() {

        private var currentSkipItems = 0

        override suspend fun initialize() = InitializeAction.LAUNCH_INITIAL_REFRESH

        override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, StatusWithGames>
        ): MediatorResult {
            try {
                val skipItemsCount: Int = when (loadType) {
                    LoadType.REFRESH -> 0
                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                    LoadType.APPEND -> {
                        val newSkipItems = state.pages.count() * state.config.pageSize

                        if (newSkipItems == currentSkipItems) {
                            return MediatorResult.Success(endOfPaginationReached = true)
                        } else {
                            currentSkipItems = newSkipItems
                        }

                        newSkipItems
                    }
                }

                val ids = fetchIds(skipItemsCount)
                val games = api.fetchGames(ids, "us", "en")
                val statusWithIdsRef =
                    games.map { game -> StatusGameCrossRef(statusId, game.productId) }

                database.withTransaction {
                    dao.insertAll(games)
                    dao.insertAllStatusGameCrossRef(statusWithIdsRef)
                }

                return MediatorResult.Success(endOfPaginationReached = games.isEmpty())
            } catch (e: IOException) {
                return MediatorResult.Error(e)
            } catch (e: HttpException) {
                return MediatorResult.Error(e)
            }
        }
    }
}
