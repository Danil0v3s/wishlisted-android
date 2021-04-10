package app.wishlisted.android.data.src.repository.game

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.wishlisted.android.data.src.api.game.GameApi
import app.wishlisted.android.data.src.model.toDomainModel
import app.wishlisted.android.domain.model.Game
import retrofit2.HttpException
import java.io.IOException

class GamesDataSource(
    private val api: GameApi,
    private val fetchIds: suspend (skipItems: Int) -> GameApi.GamesIdsResponse,
    private val language: String,
    private val market: String
) : PagingSource<Int, Game>() {
    override fun getRefreshKey(state: PagingState<Int, Game>) = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val response = fetchIds(params.key ?: 0)
            val games = api
                .fetchGames(response.data, language = language, market = market)
                .map { it.toDomainModel() }

            LoadResult.Page(
                data = games,
                prevKey = params.key,
                nextKey = response.pagingInfo.nextPage
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
