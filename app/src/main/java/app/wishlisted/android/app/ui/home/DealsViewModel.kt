package app.wishlisted.android.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import app.wishlisted.android.data.src.threading.DispatcherProvider
import app.wishlisted.android.domain.common.Result
import app.wishlisted.android.domain.model.GameCollection
import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCase
import app.wishlisted.android.domain.usecase.home.FetchHomeCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
	private val fetchGameDealsUseCase: FetchGameDealsUseCase,
	private val fetchHomeCollectionsUseCase: FetchHomeCollectionsUseCase,
	private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

	private val scope = CoroutineScope(dispatcherProvider.io)

	val deals = fetchGameDealsUseCase().cachedIn(scope)

	private val _homeCollections = MutableStateFlow(listOf<GameCollection>())
	val homeCollections: Flow<List<GameCollection>>
		get() = _homeCollections.shareIn(scope, replay = 1, started = SharingStarted.Lazily)

	init {
		viewModelScope.launch {
			fetchHomeCollections()
		}
	}

	private suspend fun fetchHomeCollections() {
		when (val result = fetchHomeCollectionsUseCase()) {
			is Result.Failure -> {

			}
			is Result.Success -> _homeCollections.emit(result.value)
		}
	}
}
