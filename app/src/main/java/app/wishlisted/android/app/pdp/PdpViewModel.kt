package app.wishlisted.android.app.pdp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.usecase.game.FetchGameDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PdpViewModel @Inject constructor(
    private val fetchGameDetailsUseCase: FetchGameDetailsUseCase
) : ViewModel() {

    private val gameId = MutableStateFlow("")

    val game: LiveData<Game>
        get() = fetchGameDetailsUseCase(FetchGameDetailsUseCase.Input(gameId.value)).asLiveData()

    fun setParams(gameId: String) {
        this.gameId.value = gameId
    }
}
