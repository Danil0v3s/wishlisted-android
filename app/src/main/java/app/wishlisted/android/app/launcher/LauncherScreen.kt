package app.wishlisted.android.app.launcher

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import app.wishlisted.android.app.main.MainViewModel
import kotlin.system.exitProcess

@Composable
fun LauncherScreen(
    state: State<MainViewModel.State>,
    onLoadingFinished: () -> Unit
) {
    when (state.value) {
        MainViewModel.State.Done -> onLoadingFinished()
        MainViewModel.State.Error -> {
            Toast.makeText(LocalContext.current, "Errored out", Toast.LENGTH_SHORT).show()
            exitProcess(0)
        }
        MainViewModel.State.Loading -> {
        }
    }
}
