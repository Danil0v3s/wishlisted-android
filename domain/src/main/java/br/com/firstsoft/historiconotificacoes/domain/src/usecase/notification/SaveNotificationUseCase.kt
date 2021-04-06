package br.com.firstsoft.historiconotificacoes.domain.src.usecase.notification

import br.com.firstsoft.historiconotificacoes.domain.src.common.Result
import br.com.firstsoft.historiconotificacoes.domain.src.common.SuspendUseCase
import br.com.firstsoft.historiconotificacoes.domain.src.model.AppNotification
import br.com.firstsoft.historiconotificacoes.domain.src.repository.NotificationRepository
import javax.inject.Inject

interface SaveNotificationUseCase : SuspendUseCase<Result<Unit>, SaveNotificationUseCase.Input> {
    data class Input(val notification: AppNotification)
}

class SaveNotificationUseCaseImpl @Inject constructor(
    private val notificationRepository: NotificationRepository
) : SaveNotificationUseCase {
    override suspend fun invoke(params: SaveNotificationUseCase.Input): Result<Unit> {
        return notificationRepository.saveNotification(params.notification)
    }
}
