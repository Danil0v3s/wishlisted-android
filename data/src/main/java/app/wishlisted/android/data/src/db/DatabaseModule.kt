package app.wishlisted.android.data.src.db

import android.content.Context
import androidx.room.Room
import app.wishlisted.android.data.src.db.migrations.Migrations
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
    fun providesNotificationDao(appDatabase: AppDatabase) = appDatabase.notificationDao()
}
