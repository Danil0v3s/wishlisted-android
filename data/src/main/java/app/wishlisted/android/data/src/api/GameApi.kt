package app.wishlisted.android.data.src.api

import app.wishlisted.android.data.src.model.GameDTO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {

    data class GamesIdsResponse(
        val data: List<String>,
        val pagingInfo: PagingInfo
    ) {
        data class PagingInfo(
            val totalItems: Int,
            val currentPage: Int,
            val nextPage: Int?
        )
    }

    @GET("games/deals")
    suspend fun fetchDeals(@Query("skipItems") skipItems: Int): GamesIdsResponse

    @GET("games/details")
    suspend fun fetchGames(
        @Query("gameIds") productIds: List<String>,
        @Query("market") market: String,
        @Query("language") language: String,
    ): List<GameDTO>

    @GET("games/status")
    suspend fun fetchStatuses(): List<String>
}

@Module
@InstallIn(SingletonComponent::class)
object GameApiModule {

    @Provides
    fun providesGameApi(retrofit: Retrofit) =
        retrofit.create(GameApi::class.java)
}