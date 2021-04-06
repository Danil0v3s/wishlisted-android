package app.wishlisted.android.domain.src.usecase

import app.wishlisted.android.domain.src.usecase.notification.GetRecentNotificationsUseCase
import app.wishlisted.android.domain.src.usecase.notification.GetRecentNotificationsUseCaseImpl
import app.wishlisted.android.domain.src.usecase.notification.SaveNotificationUseCase
import app.wishlisted.android.domain.src.usecase.notification.SaveNotificationUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindsGetRecentNotificationsUseCase(impl: GetRecentNotificationsUseCaseImpl): GetRecentNotificationsUseCase

    @Binds
    abstract fun bindsSaveNotificationUseCase(impl: SaveNotificationUseCaseImpl): SaveNotificationUseCase
}
