package app.wishlisted.android.domain.model

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
    var videos: List<Video>
) {
    class Rating(
        var averageRating: Double,
        var ratingCount: Int
    )

    class Attribute(
        var name: String,
        var minimum: Int,
        var maximum: Int
    )

    class Eligibility(
        var gamePass: Boolean,
        var eaPlay: Boolean,
        var gamePassUltimate: Boolean,
        var gold: Boolean
    )

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
    )

    class Image(
        var purpose: String,
        var uri: String
    )

    class Video(
        var purpose: String,
        var uri: String,
        var caption: String
    )
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

val Game.brandedKeyArt: String?
    get() = this.images.firstOrNull { it.purpose == GameImagePurpose.BrandedKeyArt.value }?.uri
