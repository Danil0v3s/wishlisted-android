package app.wishlisted.android.data.src.model

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import app.wishlisted.android.domain.model.Game
import com.squareup.moshi.JsonClass

@Entity(
    tableName = "tb_games",
//    indices = [
//        Index(name = "productId", unique = true)
//    ]
)
@JsonClass(generateAdapter = true)
data class GameDTO(
    val categories: List<String>,
    val rating: Rating,
    val attributes: List<Attribute>,
    val eligibility: Eligibility,
    val price: Price,
    val images: List<Image>,
    val videos: List<Video>,
    @NonNull
    @PrimaryKey(autoGenerate = false)
    val productId: String,
    val releaseDate: String,
    val productTitle: String,
    val href: String,
    val description: String,
    val shortDescription: String,
    val publisher: String,
    val market: String,
    val language: String
) {
    @JsonClass(generateAdapter = true)
    data class Rating(
        val averageRating: Double,
        val ratingCount: Int
    )

    @JsonClass(generateAdapter = true)
    data class Attribute(
        val name: String,
        val minimum: Int,
        val maximum: Int
    )

    @JsonClass(generateAdapter = true)
    data class Eligibility(
        val gamePass: Boolean,
        val eaPlay: Boolean,
        val gamePassUltimate: Boolean,
        val gold: Boolean
    )

    @JsonClass(generateAdapter = true)
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
    )

    @JsonClass(generateAdapter = true)
    data class Image(
        val purpose: String,
        val uri: String
    )

    @JsonClass(generateAdapter = true)
    data class Video(
        val purpose: String,
        val uri: String,
        val caption: String
    )
}

fun GameDTO.Attribute.toDomainModel(): Game.Attribute {
    return Game.Attribute(
        name = name,
        minimum = minimum,
        maximum = maximum
    )
}

fun GameDTO.Rating.toDomainModel(): Game.Rating {
    return Game.Rating(
        averageRating = averageRating,
        ratingCount = ratingCount
    )
}

fun GameDTO.Video.toDomainModel(): Game.Video {
    return Game.Video(
        purpose = purpose,
        uri = uri,
        caption = caption
    )
}

fun GameDTO.Eligibility.toDomainModel(): Game.Eligibility {
    return Game.Eligibility(
        gamePass,
        eaPlay,
        gamePassUltimate,
        gold
    )
}

fun GameDTO.Image.toDomainModel(): Game.Image {
    return Game.Image(
        purpose = purpose,
        uri = uri
    )
}

fun GameDTO.Price.toDomainModel(): Game.Price {
    return Game.Price(
        isPurchasable,
        isGoldSale,
        specialPrice,
        gameListPrice,
        gameMsrpPrice,
        currencyCode,
        isOnSale,
        goldPrice,
        hasGoldDiscount
    )
}

fun GameDTO.toDomainModel(): Game {
    return Game(
        productId = productId,
        rating = rating.toDomainModel(),
        attributes = attributes.map { it.toDomainModel() },
        price = price.toDomainModel(),
        eligibility = eligibility.toDomainModel(),
        images = images.map { it.toDomainModel() },
        videos = videos.map { it.toDomainModel() },
        categories = categories,
        releaseDate = releaseDate,
        description = description,
        href = href,
        language = language,
        market = market,
        productTitle = productTitle,
        publisher = publisher,
        shortDescription = shortDescription
    )
}
