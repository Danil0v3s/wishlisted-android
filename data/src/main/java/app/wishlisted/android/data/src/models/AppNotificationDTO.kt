package app.wishlisted.android.data.src.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import app.wishlisted.android.domain.src.model.AppNotification

@Entity(
    tableName = "notification",
    indices = [Index(value = ["packageName"], unique = false), Index(value = ["createdTime"], unique = true)]
)
data class AppNotificationDTO(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val appName: String? = null,
    val title: String? = null,
    val bigMessage: String? = null,
    val packageName: String? = null,
    val createdTime: String? = null,
    val extras: Map<String, String>? = null
)

fun AppNotificationDTO.toDomainModel() = AppNotification(
    id = id,
    message = bigMessage.orEmpty(),
    appName = "",
    createdTime = createdTime?.toLong() ?: 0L,
    extras = extras ?: emptyMap(),
    packageName = packageName.orEmpty(),
    title = title.orEmpty()
)

fun AppNotification.toDataModel() = AppNotificationDTO(
    id = id,
    bigMessage = message,
    appName = appName,
    createdTime = createdTime.toString(),
    title = title,
    packageName = packageName,
    extras = extras
)
