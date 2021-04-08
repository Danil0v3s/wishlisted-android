package app.wishlisted.android.app.pdp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.model.GameImagePurpose
import app.wishlisted.android.domain.model.discountAmount
import app.wishlisted.android.domain.model.getImage
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun PdpScreen(game: Game) {
    Column {
        HeroSection(game = game)
        PrimaryMetadata(game = game)
        SecondaryMetadata(game = game)
    }
}

@Composable
fun HeroSection(game: Game) {
    val posterWidth = 120
    val offset = (posterWidth / AspectRatio.ThreeByFour / 2).dp

    Box(Modifier.padding(bottom = offset)) {
        CoilImage(
            data = game.getImage(GameImagePurpose.SuperHeroArt).orEmpty(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(AspectRatio.SixteenByNine)
        )

        CoilImage(
            data = game.getImage(GameImagePurpose.Poster).orEmpty(),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(posterWidth.dp)
                .aspectRatio(AspectRatio.ThreeByFour)
                .offset(y = offset)
        )
    }
}

@Composable
fun PrimaryMetadata(game: Game) {
    val typography = MaterialTheme.typography

    Column {
        DiscountLabel(price = game.price)

        Text(
            text = game.productTitle,
            style = typography.h5,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        FlowRow(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = game.publisher,
                style = typography.button,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 4.dp)
            )

            game.categories.forEach { category ->
                Text(
                    text = category,
                    style = typography.button,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color("#0067B8".toColorInt())
                )
            }
        }

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun SecondaryMetadata(game: Game) {
    val typography = MaterialTheme.typography

    Column {
        Row(modifier = Modifier.padding(bottom = 16.dp)) {
            if (game.price.isOnSale) {
                Text(
                    text = "$${game.price.gameMsrpPrice}",
                    style = typography.h6,
                    textDecoration = TextDecoration.LineThrough,
                    color = Color.Gray
                )
            }

            Text(
                text = "$${game.price.gameListPrice}",
                style = typography.h6
            )
        }
    }
}

@Composable
fun DiscountLabel(price: Game.Price) {
    if (price.isOnSale) {
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .background(color = Color("#FFD800".toColorInt()))
                .padding(start = 15.dp, end = 15.dp, top = 3.dp, bottom = 3.dp)
        ) {
            Text(text = "Save $${price.discountAmount}")
        }
    }
}

object AspectRatio {
    const val ThreeByFour = 3 / 4f
    const val SixteenByNine = 16 / 9f
}
