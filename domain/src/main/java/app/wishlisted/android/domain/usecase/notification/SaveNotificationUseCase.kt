package app.wishlisted.android.domain.src.usecase.notification

import app.wishlisted.android.domain.src.common.Result
import app.wishlisted.android.domain.src.common.SuspendUseCase
import app.wishlisted.android.domain.src.model.AppNotification
import app.wishlisted.android.domain.src.repository.NotificationRepository
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
