package app.wishlisted.android.data.src.repository.notification

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import app.wishlisted.android.data.src.db.NotificationDao
import app.wishlisted.android.data.src.models.toDataModel
import app.wishlisted.android.data.src.models.toDomainModel
import app.wishlisted.android.domain.src.common.Result
import app.wishlisted.android.domain.src.model.AppNotification
import app.wishlisted.android.domain.src.repository.NotificationRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao
) : NotificationRepository {

    override suspend fun fetchAllNotifications() = Pager(
        config = PagingConfig(20)
    ) {
        notificationDao.fetchAll()
    }.flow.map { pagingData ->
        pagingData.map { it.toDomainModel() }
    }

    override suspend fun fetchRecentNotifications() =
        notificationDao
            .fetchRecentNotifications()
            .map { list -> list.map { it.toDomainModel() } }

    override suspend fun saveNotification(notification: AppNotification): Result<Unit> {
        return try {
            notificationDao.insertOne(notification.toDataModel())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}
