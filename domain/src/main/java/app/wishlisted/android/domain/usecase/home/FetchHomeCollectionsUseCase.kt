package app.wishlisted.android.domain.usecase.home

import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.common.SuspendUseCaseWithoutParams
import app.wishlisted.android.domain.model.GameCollection
import app.wishlisted.android.domain.repository.HomeRepository
import javax.inject.Inject

interface FetchHomeCollectionsUseCase : SuspendUseCaseWithoutParams<Result<List<GameCollection>>>

class FetchHomeCollectionsUseCaseImpl @Inject constructor(
	private val homeRepository: HomeRepository
) : FetchHomeCollectionsUseCase {
	override suspend fun invoke(): Result<List<GameCollection>> {
		return homeRepository.fetchHomeCollections()
	}
}