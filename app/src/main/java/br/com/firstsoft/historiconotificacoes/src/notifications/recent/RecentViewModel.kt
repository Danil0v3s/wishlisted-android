package br.com.firstsoft.historiconotificacoes.src.notifications.recent

import androidx.lifecycle.ViewModel
import br.com.firstsoft.historiconotificacoes.domain.src.model.AppNotification
import br.com.firstsoft.historiconotificacoes.domain.src.usecase.notification.GetRecentNotificationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val getRecentNotificationsUseCase: GetRecentNotificationsUseCase
) : ViewModel() {

    @OptIn(InternalCoroutinesApi::class)
    val recentNotifications: Flow<List<AppNotification>>
        get() = flow {
            getRecentNotificationsUseCase().collect(this)
        }
}