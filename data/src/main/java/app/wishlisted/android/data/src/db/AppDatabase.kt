package app.wishlisted.android.data.src.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.wishlisted.android.data.src.db.converters.MapTypeConverter
import app.wishlisted.android.data.src.models.AppNotificationDTO

@Database(entities = [AppNotificationDTO::class], version = 3, exportSchema = true)
@TypeConverters(MapTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}
