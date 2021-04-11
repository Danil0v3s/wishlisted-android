package app.wishlisted.android.app.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.wishlisted.android.domain.usecase.game.FetchGameStatusesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    private val fetchStatusUseCase: FetchGameStatusesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: LiveData<State>
        get() = _state.asLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchStatuses()
        }
    }

    private suspend fun fetchStatuses() {
        fetchStatusUseCase().fold(
            onSuccess = {
                _state.value = State.Done
            },
            onFailure = {
                it.printStackTrace()
                _state.value = State.Error
            }
        )
    }

    sealed class State {
        object Loading : State()
        object Done : State()
        object Error : State()
    }
}
