package app.wishlisted.android.domain.model

import java.io.Serializable
import java.math.RoundingMode
import java.text.DecimalFormat

class Game(
	var productId: String,
	var categories: List<String>,
	var releaseDate: String,
	var rating: Rating,
	var attributes: List<Attribute>,
	var productTitle: String,
	var eligibility: Eligibility,
	var price: Price,
	var images: List<Image>,
	var href: String,
	var description: String,
	var shortDescription: String,
	var videos: List<Video>,
	var publisher: String
) : Serializable {
	class Rating(
		var averageRating: Double,
		var ratingCount: Int
	) : Serializable

	class Attribute(
		var name: String,
		var minimum: Int,
		var maximum: Int
	) : Serializable

	class Eligibility(
		var gamePass: Boolean,
		var eaPlay: Boolean,
		var gamePassUltimate: Boolean,
		var gold: Boolean
	) : Serializable

	class Price(
		var isPurchasable: Boolean,
		var isGoldSale: Boolean,
		var specialPrice: Double,
		var gameListPrice: Double,
		var gameMsrpPrice: Double,
		var currencyCode: String,
		var isOnSale: Boolean,
		var goldPrice: Double,
		var hasGoldDiscount: Boolean
	) : Serializable

	class Image(
		var purpose: String,
		var uri: String
	) : Serializable

	class Video(
		var purpose: String,
		var uri: String,
		var caption: String
	) : Serializable
}

enum class GameImagePurpose(val value: String) {
	BrandedKeyArt("BrandedKeyArt"),
	TitledHeroArt("TitledHeroArt"),
	Poster("Poster"),
	SuperHeroArt("SuperHeroArt"),
	BoxArt("BoxArt"),
	FeaturePromotionalSquareArt("FeaturePromotionalSquareArt"),
	Logo("Logo"),
	Screenshot("Screenshot")
}

fun Game.getImage(imagePurpose: GameImagePurpose): String? {
	return this.images.firstOrNull { it.purpose == imagePurpose.value }?.uri
}

val Game.gameplayTrailer: String?
	get() = this.videos.firstOrNull { it.purpose == "trailer" }?.uri

val Game.Price.discountAmount: Double
	get() {
		return DecimalFormat("#.##").apply {
			roundingMode = RoundingMode.FLOOR
		}.format(this.gameMsrpPrice - this.gameListPrice).toDouble()
	}