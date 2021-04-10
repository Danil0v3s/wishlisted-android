package app.wishlisted.android.data.src.repository.game

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.flatMap
import androidx.room.withTransaction
import app.wishlisted.android.data.src.api.game.GameApi
import app.wishlisted.android.data.src.db.AppDatabase
import app.wishlisted.android.data.src.db.dao.GameDao
import app.wishlisted.android.data.src.db.dao.GameStatusRemoteKeyDao
import app.wishlisted.android.data.src.db.dao.StatusDao
import app.wishlisted.android.data.src.model.GameStatusRemoteKey
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
    private val remoteKeyDao: GameStatusRemoteKeyDao,
    private val appDatabase: AppDatabase
) : GameRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun fetchGameDeals(): Flow<PagingData<Game>> {
        val statusId = statusDao.fetchAll().firstOrNull { it.name == "Deals" }?.statusId ?: 0

        val mediator = PageKeyedRemoteMediator(
            database = appDatabase,
            api = gameApi,
            dao = gameDao,
            fetchIds = { gameApi.fetchDeals(it) },
            remoteKeyDao = remoteKeyDao,
            statusId = statusId
        )

        return Pager(
            config = PagingConfig(20),
            remoteMediator = mediator
        ) {
            gameDao.gamesByStatus(statusId)
        }.flow.map { pagingData ->
            pagingData.flatMap { statusWithGames ->
                statusWithGames.games.map { it.toDomainModel() }
            }
        }
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

    @OptIn(ExperimentalPagingApi::class)
    class PageKeyedRemoteMediator(
        private val database: AppDatabase,
        private val api: GameApi,
        private val dao: GameDao,
        private val remoteKeyDao: GameStatusRemoteKeyDao,
        private val fetchIds: suspend (skipItems: Int) -> GameApi.GamesIdsResponse,
        private val statusId: Int
    ) : RemoteMediator<Int, StatusWithGames>() {

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
                        val remoteKey = database.withTransaction {
                            remoteKeyDao.remoteKeyByStatus(statusId)
                        }

                        if (remoteKey.nextPage == null) {
                            return MediatorResult.Success(endOfPaginationReached = true)
                        }

                        remoteKey.nextPage
                    }
                }

                val idsResponse = fetchIds(skipItemsCount)
                val games = api.fetchGames(idsResponse.data, "us", "en")
                val statusWithIdsRef =
                    games.map { game -> StatusGameCrossRef(statusId, game.productId) }

                database.withTransaction {
                    remoteKeyDao.insert(
                        GameStatusRemoteKey(
                            statusId,
                            idsResponse.pagingInfo.nextPage
                        )
                    )
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
