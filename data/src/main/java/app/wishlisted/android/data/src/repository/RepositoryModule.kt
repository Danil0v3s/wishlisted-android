package app.wishlisted.android.data.src.repository

import app.wishlisted.android.data.src.repository.notification.NotificationRepositoryImpl
import app.wishlisted.android.domain.src.repository.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsNotificationRepository(impl: NotificationRepositoryImpl): NotificationRepository
}
