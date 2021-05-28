package app.wishlisted.android.app.home.deals

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.wishlisted.android.data.src.threading.DispatcherProvider
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
	private val fetchGameDealsUseCase: FetchGameDealsUseCase,
	private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

	private val scope = CoroutineScope(dispatcherProvider.io)

	val deals = fetchGameDealsUseCase().cachedIn(scope)
}
