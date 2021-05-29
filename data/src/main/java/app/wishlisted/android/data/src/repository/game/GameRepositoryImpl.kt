package app.wishlisted.android.data.src.repository.game

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import app.wishlisted.android.data.src.api.game.GameApi
import app.wishlisted.android.data.src.db.AppDatabase
import app.wishlisted.android.data.src.db.dao.GameDao
import app.wishlisted.android.data.src.db.dao.GameStatusRemoteKeyDao
import app.wishlisted.android.data.src.db.dao.StatusDao
import app.wishlisted.android.data.src.model.StatusDTO
import app.wishlisted.android.data.src.model.toDomainModel
import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameApi: GameApi,
    private val gameDao: GameDao,
    private val statusDao: StatusDao,
    private val remoteKeyDao: GameStatusRemoteKeyDao,
    private val appDatabase: AppDatabase
) : GameRepository {

    @ExperimentalCoroutinesApi
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchGameDeals(): Flow<PagingData<Game>> {
        var statusId = 0
        val mediator = PageKeyedRemoteMediator(
            database = appDatabase,
            api = gameApi,
            dao = gameDao,
            fetchIds = { gameApi.fetchDeals(it) },
            fetchStatusId = {
                statusId = statusDao.fetchAll().firstOrNull { it.name == "Deals" }?.statusId ?: 0
                statusId
            },
            remoteKeyDao = remoteKeyDao
        )

        return Pager(
            config = PagingConfig(20),
            remoteMediator = mediator
        ) {
            gameDao.gamesByStatus(statusId)
        }.flow.mapLatest { pagingData -> pagingData.map { it.toDomainModel() } }
    }

    override suspend fun fetchStatus(): Result<Unit> {
        return try {
            val res = try {
                gameApi.fetchStatuses()
            } catch (ex: Exception) {
                emptyList()
            }
            val status = res.mapIndexed { index, s ->
                StatusDTO(statusId = index, name = s)
            }

            statusDao.insertAll(status)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}
