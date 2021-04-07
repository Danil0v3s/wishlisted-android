package app.wishlisted.android.app.pdp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import app.wishlisted.android.app.views.player.Player
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.model.brandedKeyArt
import app.wishlisted.android.domain.model.discountAmount
import app.wishlisted.android.domain.model.gameplayTrailer
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun PdpScreen(game: Game) {
	Column {
		PrimaryMetadata(game = game)
		SecondaryMetadata(game = game)
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
fun PrimaryMetadata(game: Game) {
	val typography = MaterialTheme.typography

	Column {
		Player(uri = game.gameplayTrailer.orEmpty())

		GameBoxArt(
			game = game,
			modifier = Modifier
				.align(Alignment.CenterHorizontally)
				.padding(0.dp)
		)

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
fun GameBoxArt(game: Game, modifier: Modifier) {
	val width = 120
	val aspectRatio = 3 / 4f
	Row(
		modifier = modifier.offset(y = -(width / aspectRatio / 2).dp)
	) {
		CoilImage(
			data = game.brandedKeyArt.orEmpty(),
			contentDescription = null,
			modifier = Modifier
				.width(width.dp)
				.aspectRatio(aspectRatio)
		)
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