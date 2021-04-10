package app.wishlisted.android.app.home.deals

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val fetchGameDealsUseCase: FetchGameDealsUseCase
) : ViewModel() {

    @OptIn(InternalCoroutinesApi::class)
    val deals: Flow<PagingData<Game>>
        get() = flow {
            fetchGameDealsUseCase().collect(this)
        }
}
