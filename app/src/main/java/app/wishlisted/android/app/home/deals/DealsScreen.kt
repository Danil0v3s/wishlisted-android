package app.wishlisted.android.app.home.deals

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.model.GameImagePurpose
import app.wishlisted.android.domain.model.getImage
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalFoundationApi
@Composable
fun DealsScreen(
    onItemClick: (Game) -> Unit
) {
    val dealsViewModel: DealsViewModel = hiltNavGraphViewModel()

    val deals: LazyPagingItems<Game> = dealsViewModel.deals.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    MaterialTheme {
        LazyColumn(state = listState) {
            items(deals) { game ->
                DealsContent(game!!, onItemClick)
            }
        }
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

        CoilImage(
            data = game.getImage(GameImagePurpose.Poster).orEmpty(),
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