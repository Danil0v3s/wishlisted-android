package app.wishlisted.android.data.src.db

import android.content.Context
import androidx.room.Room
import app.wishlisted.android.data.src.db.dao.GameDao
import app.wishlisted.android.data.src.db.dao.GameStatusRemoteKeyDao
import app.wishlisted.android.data.src.db.dao.StatusDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "wishlisted"
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesGameDao(database: AppDatabase): GameDao {
        return database.gameDao()
    }

    @Provides
    @Singleton
    fun providesStatusDao(database: AppDatabase): StatusDao {
        return database.statusDao()
    }

    @Provides
    @Singleton
    fun providesGameStatusRemoteKeyDao(database: AppDatabase): GameStatusRemoteKeyDao {
        return database.remoteKeyDao()
    }
}
