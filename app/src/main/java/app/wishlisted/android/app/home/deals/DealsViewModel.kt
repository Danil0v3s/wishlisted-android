package app.wishlisted.android.app.home.deals

import androidx.lifecycle.ViewModel
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val fetchGameDealsUseCase: FetchGameDealsUseCase
) : ViewModel() {

    @OptIn(InternalCoroutinesApi::class)
    val deals: Flow<List<Game>>
        get() = flow {
            fetchGameDealsUseCase().fold(
                onSuccess = { this.emit(it) },
                onFailure = { it.printStackTrace() }
            )
        }
}
