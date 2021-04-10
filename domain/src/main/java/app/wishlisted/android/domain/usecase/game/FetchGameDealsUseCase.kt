package app.wishlisted.android.domain.usecase.game

import androidx.paging.PagingData
import app.wishlisted.android.domain.common.FlowUseCaseWithoutParams
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FetchGameDealsUseCase : FlowUseCaseWithoutParams<PagingData<Game>>

class FetchGameDealsUseCaseImpl @Inject constructor(
    private val repository: GameRepository
) : FetchGameDealsUseCase {
    override fun invoke(): Flow<PagingData<Game>> {
        return repository.fetchGameDeals()
    }
}
