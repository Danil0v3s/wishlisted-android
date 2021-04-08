package app.wishlisted.android.domain.usecase.game

import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.common.SuspendUseCaseWithoutParams
import app.wishlisted.android.domain.repository.GameRepository
import javax.inject.Inject

interface FetchGameStatusesUseCase : SuspendUseCaseWithoutParams<Result<Unit>>

class FetchGameStatusesUseCaseImpl @Inject constructor(
    private val repository: GameRepository
) : FetchGameStatusesUseCase {
    override suspend fun invoke(): Result<Unit> {
        return repository.fetchStatus()
    }
}
