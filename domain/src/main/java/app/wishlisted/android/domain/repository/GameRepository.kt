package app.wishlisted.android.domain.repository

import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.Game

interface GameRepository {
    suspend fun fetchGameDeals(): Result<List<Game>>
}
