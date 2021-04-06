package br.com.firstsoft.historiconotificacoes.domain.src.usecase

import br.com.firstsoft.historiconotificacoes.domain.src.usecase.notification.GetRecentNotificationsUseCase
import br.com.firstsoft.historiconotificacoes.domain.src.usecase.notification.GetRecentNotificationsUseCaseImpl
import br.com.firstsoft.historiconotificacoes.domain.src.usecase.notification.SaveNotificationUseCase
import br.com.firstsoft.historiconotificacoes.domain.src.usecase.notification.SaveNotificationUseCaseImpl
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