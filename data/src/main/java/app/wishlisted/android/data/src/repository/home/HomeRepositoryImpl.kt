package app.wishlisted.android.data.src.repository.home

import app.wishlisted.android.data.src.api.HomeApi
import app.wishlisted.android.data.src.model.toDomainModel
import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.GameCollection
import app.wishlisted.android.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
	private val homeApi: HomeApi
) : HomeRepository {
	override suspend fun fetchHomeCollections(): Result<List<GameCollection>> {
		return try {
			val res = homeApi.fetchHomeCollections().map {
				it.toDomainModel()
			}
			Result.Success(res)
		} catch (e: Exception) {
			Result.Failure(e)
		}
	}
}
