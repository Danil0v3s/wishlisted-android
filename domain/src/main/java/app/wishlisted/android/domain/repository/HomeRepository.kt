package app.wishlisted.android.domain.repository

import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.GameCollection

interface HomeRepository {
	suspend fun fetchHomeCollections(): Result<List<GameCollection>>
}