package app.wishlisted.android.data.src.model

import androidx.room.Entity

@Entity(primaryKeys = ["statusId"])
data class GameStatusRemoteKey(
    val statusId: Int,
    val nextPage: Int?
)
