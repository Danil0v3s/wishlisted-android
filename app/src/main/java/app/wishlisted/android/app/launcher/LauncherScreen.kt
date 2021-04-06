package app.wishlisted.android.app.launcher

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
fun LauncherScreen(
    isPermissionGiven: State<Boolean>,
    onPermissionGiven: () -> Unit
) {
    onPermissionGiven()
}
