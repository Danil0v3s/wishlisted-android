package app.wishlisted.android.app.notifications.recent

import androidx.compose.foundation.ExperimentalFoundationApi
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
fun RecentScreen(
    recentViewModel: RecentViewModel = viewModel()
) {
    val deals by recentViewModel.deals.collectAsState(initial = listOf())

    MaterialTheme {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(80.dp),
        ) {
            items(deals) { game ->
                RecentContent(game)
            }
        }
    }
}

@Composable
fun RecentContent(game: Game) {
    val typography = MaterialTheme.typography

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
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
