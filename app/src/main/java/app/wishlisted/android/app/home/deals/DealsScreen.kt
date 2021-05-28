package app.wishlisted.android.app.home.deals

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.model.GameImagePurpose
import app.wishlisted.android.domain.model.getImage
import com.google.accompanist.coil.rememberCoilPainter

@ExperimentalFoundationApi
@Composable
fun DealsScreen(
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues(0.dp),
	deals: LazyPagingItems<Game>,
	onItemClick: (Game) -> Unit,
) {
	val nColumns = 3

	LazyVerticalGrid(
		cells = GridCells.Fixed(nColumns),
		modifier = Modifier
			.fillMaxSize()
			.composed { modifier },
		contentPadding = contentPadding,
	) {
		items(deals) { game ->
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
