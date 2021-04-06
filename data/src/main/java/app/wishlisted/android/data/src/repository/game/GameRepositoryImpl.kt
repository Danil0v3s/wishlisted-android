package app.wishlisted.android.data.src.repository.game

import app.wishlisted.android.data.src.api.game.GameApi
import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameApi: GameApi
) : GameRepository {
    override suspend fun fetchGameDeals(): Result<List<Game>> {
        return try {
            Result.Success(gameApi.fetchDeal())
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}
