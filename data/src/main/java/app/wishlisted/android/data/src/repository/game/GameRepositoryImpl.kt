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
    private val statusDao: StatusDao
) : GameRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchGameDeals(): Flow<PagingData<Game>> {
        val dataSource = GamesDataSource(
            api = gameApi,
            fetchIds = { gameApi.fetchDeals(it) },
            market = "us",
            language = "en"
        )

        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { dataSource }
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
}
