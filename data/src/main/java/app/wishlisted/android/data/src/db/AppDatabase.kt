package app.wishlisted.android.data.src.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.wishlisted.android.data.src.db.dao.GameDao
import app.wishlisted.android.data.src.model.GameDTO

@Database(entities = [GameDTO::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
