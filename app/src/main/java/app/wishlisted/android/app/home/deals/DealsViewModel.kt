package app.wishlisted.android.app.home.deals

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val fetchGameDealsUseCase: FetchGameDealsUseCase
) : ViewModel() {

    val deals: Flow<PagingData<Game>>
        get() = fetchGameDealsUseCase()
}
