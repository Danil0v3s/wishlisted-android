package app.wishlisted.android.domain.repository

import androidx.paging.PagingData
import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun fetchGameDeals(): Flow<PagingData<Game>>
    fun fetchGameDetails(productId: String): Flow<Game>

    suspend fun fetchStatus(): Result<Unit>
}
