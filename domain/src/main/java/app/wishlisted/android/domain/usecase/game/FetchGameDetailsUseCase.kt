package app.wishlisted.android.domain.usecase.game

import app.wishlisted.android.domain.common.FlowUseCase
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FetchGameDetailsUseCase : FlowUseCase<Game, FetchGameDetailsUseCase.Input> {
    data class Input(val productId: String)
}

class FetchGameDetailsUseCaseImpl @Inject constructor(
    private val gameRepository: GameRepository
) : FetchGameDetailsUseCase {
    override fun invoke(params: FetchGameDetailsUseCase.Input): Flow<Game> {
        return gameRepository.fetchGameDetails(params.productId)
    }
}
