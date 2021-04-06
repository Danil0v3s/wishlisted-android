package app.wishlisted.android.app.notifications.recent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.wishlisted.android.R
import app.wishlisted.android.domain.model.Game
import com.google.accompanist.coil.CoilImage

@Composable
fun RecentScreen(
    recentViewModel: RecentViewModel = viewModel()
) {
    val deals by recentViewModel.deals.collectAsState(initial = listOf())

    MaterialTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        CoilImage(
            data = game.images.first().uri,
            contentDescription = null,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(4.dp))
                .background(Color.Blue)
                .height(64.dp)
                .width(64.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                game.productTitle,
                style = typography.h6
            )

            Text(
                game.shortDescription,
                style = typography.body1
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}
