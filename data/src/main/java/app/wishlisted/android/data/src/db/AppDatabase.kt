package app.wishlisted.android.data.src.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.wishlisted.android.data.src.db.dao.GameDao
import app.wishlisted.android.data.src.db.dao.StatusDao
import app.wishlisted.android.data.src.model.GameDTO
import app.wishlisted.android.data.src.model.StatusDTO
import app.wishlisted.android.data.src.model.StatusGameCrossRef

@Database(
    entities = [GameDTO::class, StatusDTO::class, StatusGameCrossRef::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    GameImageConverters::class,
    GameVideoConverters::class,
    StringConverters::class,
    AttributeConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun statusDao(): StatusDao
}
