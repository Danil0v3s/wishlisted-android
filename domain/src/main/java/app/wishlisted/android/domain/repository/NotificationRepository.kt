package app.wishlisted.android.domain.src.repository

import androidx.paging.PagingData
import app.wishlisted.android.domain.src.common.Result
import app.wishlisted.android.domain.src.model.AppNotification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun fetchAllNotifications(): Flow<PagingData<AppNotification>>
    suspend fun fetchRecentNotifications(): Flow<List<AppNotification>>
    suspend fun saveNotification(notification: AppNotification): Result<Unit>
}
