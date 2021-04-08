package app.wishlisted.android.data.src.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_status")
data class StatusDTO(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val statusId: Int,
    val name: String
)
