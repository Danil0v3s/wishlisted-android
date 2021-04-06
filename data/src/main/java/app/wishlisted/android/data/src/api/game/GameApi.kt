package app.wishlisted.android.data.src.api.game

import app.wishlisted.android.domain.model.Game
import retrofit2.http.GET

interface GameApi {

    @GET("games/deals")
    suspend fun fetchDeal(): List<Game>
}