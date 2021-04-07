package app.wishlisted.android.app.home.deals

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.model.brandedKeyArt
import com.google.accompanist.coil.CoilImage

@ExperimentalFoundationApi
@Composable
fun DealsScreen(
    dealsViewModel: DealsViewModel = viewModel(),
    onItemClick: (Game) -> Unit
) {
    val deals by dealsViewModel.deals.collectAsState(initial = listOf())

    MaterialTheme {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
        ) {
            items(deals) { game ->
                DealsContent(game, onItemClick)
            }
        }
    }
}

@Composable
fun DealsContent(game: Game, onItemClick: (Game) -> Unit) {
    val typography = MaterialTheme.typography

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onItemClick(game) }
    ) {

        CoilImage(
            data = game.brandedKeyArt.orEmpty(),
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
