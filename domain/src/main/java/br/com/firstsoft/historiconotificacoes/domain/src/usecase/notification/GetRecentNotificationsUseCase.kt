package br.com.firstsoft.historiconotificacoes.domain.src.usecase.notification

import br.com.firstsoft.historiconotificacoes.domain.src.common.SuspendUseCaseWithoutParams
import br.com.firstsoft.historiconotificacoes.domain.src.model.AppNotification
import br.com.firstsoft.historiconotificacoes.domain.src.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRecentNotificationsUseCase : SuspendUseCaseWithoutParams<Flow<List<AppNotification>>>

class GetRecentNotificationsUseCaseImpl @Inject constructor(
    private val notificationRepository: NotificationRepository
) : GetRecentNotificationsUseCase {
    override suspend fun invoke(): Flow<List<AppNotification>> {
        return notificationRepository.fetchRecentNotifications()
    }
}
