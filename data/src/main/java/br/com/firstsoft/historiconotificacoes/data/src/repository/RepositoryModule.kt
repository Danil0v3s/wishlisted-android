package br.com.firstsoft.historiconotificacoes.data.src.repository

import br.com.firstsoft.historiconotificacoes.data.src.repository.notification.NotificationRepositoryImpl
import br.com.firstsoft.historiconotificacoes.domain.src.repository.NotificationRepository
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
