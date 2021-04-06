package app.wishlisted.android.domain.usecase.game

import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.common.SuspendUseCaseWithoutParams
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.repository.GameRepository
import javax.inject.Inject

interface FetchGameDealsUseCase : SuspendUseCaseWithoutParams<Result<List<Game>>>

class FetchGameDealsUseCaseImpl @Inject constructor(
    private val repository: GameRepository
) : FetchGameDealsUseCase {
    override suspend fun invoke(): Result<List<Game>> {
        return repository.fetchGameDeals()
    }
}
