package app.wishlisted.android.app.ui.home.deals

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.model.GameImagePurpose
import app.wishlisted.android.domain.model.getImage
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun DealsScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    deals: Flow<PagingData<Game>>,
    onItemClick: (Game) -> Unit,
) {
    val nColumns = 3
    val content = deals.collectAsLazyPagingItems()

    LazyVerticalGrid(
        cells = GridCells.Fixed(nColumns),
        modifier = Modifier
            .fillMaxSize()
            .composed { modifier },
        contentPadding = contentPadding,
    ) {
        items(content) { game ->
            game?.let {
                DealsContent(game, onItemClick)
            }
        }
    }
}

@ExperimentalFoundationApi
public fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems.getAsState(index).value)
    }
}

@Composable
fun DealsContent(game: Game, onItemClick: (Game) -> Unit) {
    val typography = MaterialTheme.typography

    Column(
        modifier = Modifier
            .width(90.dp)
            .padding(4.dp)
            .clickable { onItemClick(game) }
    ) {

        Image(
            painter = rememberCoilPainter(game.getImage(GameImagePurpose.Poster).orEmpty()),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
        )

        Text(
            text = game.productTitle,
            style = typography.body2,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
