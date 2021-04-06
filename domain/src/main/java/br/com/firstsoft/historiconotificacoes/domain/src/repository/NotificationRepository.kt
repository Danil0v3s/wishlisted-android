package br.com.firstsoft.historiconotificacoes.domain.src.repository

import androidx.paging.PagingData
import br.com.firstsoft.historiconotificacoes.domain.src.common.Result
import br.com.firstsoft.historiconotificacoes.domain.src.model.AppNotification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun fetchAllNotifications(): Flow<PagingData<AppNotification>>
    suspend fun fetchRecentNotifications(): Flow<List<AppNotification>>
    suspend fun saveNotification(notification: AppNotification): Result<Unit>
}
