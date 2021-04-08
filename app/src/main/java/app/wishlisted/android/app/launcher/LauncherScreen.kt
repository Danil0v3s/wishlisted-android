package app.wishlisted.android.app.launcher

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import app.wishlisted.android.app.main.MainViewModel
import kotlin.system.exitProcess

@Composable
fun LauncherScreen(
    state: State<MainViewModel.State>,
    onLoadingFinished: () -> Unit
) {
    when (state.value) {
        MainViewModel.State.Done -> {
            exitProcess(0)
        }
        MainViewModel.State.Error -> onLoadingFinished()
        MainViewModel.State.Loading -> {
        }
    }
}
