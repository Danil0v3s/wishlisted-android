package app.wishlisted.android.data.src.api.game

import app.wishlisted.android.data.src.model.GameDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {

    @GET("games/deals")
    suspend fun fetchDeal(): List<String>

    @GET("games/details")
    suspend fun fetchGames(
        @Query("gameIds") productIds: List<String>,
        @Query("market") market: String,
        @Query("language") language: String,
    ): List<GameDTO>

    @GET("games/status")
    suspend fun fetchStatuses(): List<String>
}
