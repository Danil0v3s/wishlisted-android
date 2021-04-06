package br.com.firstsoft.historiconotificacoes.src.launcher

import android.content.Intent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext

@Composable
fun LauncherScreen(
    isPermissionGiven: State<Boolean>,
    onPermissionGiven: () -> Unit
) {
    val context = LocalContext.current

    if (!isPermissionGiven.value) {
        Button(onClick = {
            val requestIntent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            requestIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(requestIntent)
        }) {
            Text(text = "Dar acesso")
        }
    } else {
        onPermissionGiven()
    }
}