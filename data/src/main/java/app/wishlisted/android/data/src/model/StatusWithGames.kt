package app.wishlisted.android.data.src.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["statusId", "productId"])
data class StatusGameCrossRef(
    val statusId: Int,
    val productId: String
)

data class StatusWithGames(
    @Embedded val status: StatusDTO,
    @Relation(
        parentColumn = "statusId",
        entityColumn = "productId",
        associateBy = Junction(StatusGameCrossRef::class)
    )
    val games: List<GameDTO>
)
