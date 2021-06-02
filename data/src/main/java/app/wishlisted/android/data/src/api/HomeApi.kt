package app.wishlisted.android.data.src.api

import app.wishlisted.android.data.src.model.GameCollectionDTO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.http.GET

interface HomeApi {

	@GET("home/collections")
	suspend fun fetchHomeCollections(): List<GameCollectionDTO>
}

@Module
@InstallIn(SingletonComponent::class)
object HomeApiModule {

	@Provides
	fun providesHomeApi(retrofit: Retrofit) = retrofit.create(HomeApi::class.java)
}