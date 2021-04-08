package app.wishlisted.android.app.views.player

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes

@Composable
fun Player(uri: String) {
    val context = LocalContext.current
    val exoPlayer = remember { SimpleExoPlayer.Builder(context).build() }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.7f),
        factory = { PlayerView(context) }
    ) {
        it.apply {
            player = exoPlayer.apply {
                val mediaItem = MediaItem.Builder()
                    .setMimeType(MimeTypes.APPLICATION_SS)
                    .setUri(uri)
                    .build()
                setMediaItem(mediaItem)
                prepare()
            }
        }
    }
}
