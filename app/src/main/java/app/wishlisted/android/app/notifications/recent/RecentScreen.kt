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
import app.wishlisted.android.domain.src.model.AppNotification

@Composable
fun RecentScreen(
    recentViewModel: RecentViewModel = viewModel()
) {
    val notifications by recentViewModel.recentNotifications.collectAsState(initial = listOf())

    val items = listOf(
        AppNotification(0, "Discord", "Message 1", "", "", 0L, emptyMap()),
        AppNotification(0, "Whatsapp", "Message 2", "", "", 0L, emptyMap()),
        AppNotification(0, "Twitter", "Message 3", "", "", 0L, emptyMap()),
        AppNotification(0, "Google", "Message 4", "", "", 0L, emptyMap())
    )

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            RecentContent(notifications = items)
        }
    }
}

@Composable
fun RecentContent(notifications: List<AppNotification>) {
    val typography = MaterialTheme.typography

    notifications.forEach {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
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
                    it.appName,
                    style = typography.h6
                )

                Text(
                    it.title,
                    style = typography.body1
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}
