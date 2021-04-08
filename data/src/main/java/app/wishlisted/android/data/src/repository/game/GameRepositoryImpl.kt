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
import app.wishlisted.android.data.src.db.dao.StatusDao
import app.wishlisted.android.data.src.model.StatusDTO
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
        val mediator = PageKeyedRemoteMediator(
            database = appDatabase,
            api = gameApi,
            dao = gameDao,
            fetchIds = { gameApi.fetchDeal() }
        )

        return Pager(
            config = PagingConfig(20),
            remoteMediator = mediator
        ) {
            gameDao.gamesByStatus(statusId)
        }.flow.map { pagingData ->
            pagingData.flatMap {
                it.games.map { gameDto ->
                    gameDto.toDomainModel()
                }
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
        private val fetchIds: suspend (skipItems: Int) -> List<String>
    ) : RemoteMediator<Int, StatusWithGames>() {

        override suspend fun initialize(): InitializeAction {
            return InitializeAction.LAUNCH_INITIAL_REFRESH
        }

        override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, StatusWithGames>
        ): MediatorResult {
            try {
                val skipItemsCount: Int = when (loadType) {
                    LoadType.REFRESH -> 0
                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                    LoadType.APPEND -> state.pages.count() * state.config.pageSize
                }

                val ids = fetchIds(skipItemsCount)

                if (ids.isEmpty()) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                val games = api.fetchGames(ids)

                database.withTransaction {
                    dao.insertAll(games)
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
