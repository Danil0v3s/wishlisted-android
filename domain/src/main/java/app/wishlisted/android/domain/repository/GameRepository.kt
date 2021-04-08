package app.wishlisted.android.domain.repository

import androidx.paging.PagingData
import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun fetchGameDeals(): Flow<PagingData<Game>>
    suspend fun fetchStatus(): Result<Unit>
}
