package app.wishlisted.android.data.src.repository.game

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import app.wishlisted.android.data.src.api.game.GameApi
import app.wishlisted.android.data.src.db.AppDatabase
import app.wishlisted.android.data.src.db.dao.GameDao
import app.wishlisted.android.data.src.db.dao.GameStatusRemoteKeyDao
import app.wishlisted.android.data.src.model.GameStatusRemoteKey
import app.wishlisted.android.data.src.model.StatusGameCrossRef
import app.wishlisted.android.data.src.model.StatusWithGames
import retrofit2.HttpException
import java.io.IOException

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