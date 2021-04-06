package app.wishlisted.android.domain.src.usecase.notification

import app.wishlisted.android.domain.src.common.SuspendUseCaseWithoutParams
import app.wishlisted.android.domain.src.model.AppNotification
import app.wishlisted.android.domain.src.repository.NotificationRepository
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
