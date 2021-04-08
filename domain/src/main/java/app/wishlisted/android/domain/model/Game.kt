package app.wishlisted.android.domain.model

import java.io.Serializable
import java.math.RoundingMode
import java.text.DecimalFormat

data class Game(
    val productId: String,
    val categories: List<String>,
    val releaseDate: String,
    val rating: Rating,
    val attributes: List<Attribute>,
    val productTitle: String,
    val eligibility: Eligibility,
    val price: Price,
    val images: List<Image>,
    val href: String,
    val description: String,
    val shortDescription: String,
    val videos: List<Video>,
    val publisher: String,
    val market: String,
    val language: String
) : Serializable {
    data class Rating(
        val averageRating: Double,
        val ratingCount: Int
    ) : Serializable

    data class Attribute(
        val name: String,
        val minimum: Int,
        val maximum: Int
    ) : Serializable

    data class Eligibility(
        val gamePass: Boolean,
        val eaPlay: Boolean,
        val gamePassUltimate: Boolean,
        val gold: Boolean
    ) : Serializable

    data class Price(
        val isPurchasable: Boolean,
        val isGoldSale: Boolean,
        val specialPrice: Double,
        val gameListPrice: Double,
        val gameMsrpPrice: Double,
        val currencyCode: String,
        val isOnSale: Boolean,
        val goldPrice: Double,
        val hasGoldDiscount: Boolean
    ) : Serializable

    data class Image(
        val purpose: String,
        val uri: String
    ) : Serializable

    data class Video(
        val purpose: String,
        val uri: String,
        val caption: String
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
