package br.com.firstsoft.historiconotificacoes.src.main

import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import br.com.firstsoft.historiconotificacoes.src.launcher.LauncherScreen
import br.com.firstsoft.historiconotificacoes.src.notifications.recent.RecentScreen
import br.com.firstsoft.historiconotificacoes.src.notifications.recent.RecentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            Scaffold(
                topBar = {},
                backgroundColor = Color.White,
                content = {

                    NavHost(
                        navController = navController,
                        startDestination = "launcher"
                    ) {

                        composable("launcher") {
                            LauncherScreen(
                                isPermissionGiven = viewModel.isPermissionGiven.collectAsState(),
                                onPermissionGiven = {
                                    navController.navigate("notifications") {
                                        popUpTo("launcher") { inclusive = true }
                                    }
                                })
                        }

                        composable("notifications") {
                            val recentViewModel = hiltNavGraphViewModel<RecentViewModel>()
                            RecentScreen(
                                recentViewModel = recentViewModel
                            )
                        }
                    }
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onPermissionChanged(checkNotificationListeningPermission())
    }

    private fun checkNotificationListeningPermission(): Boolean {
        val notificationListenerString =
            Settings.Secure.getString(this.contentResolver, "enabled_notification_listeners")
        return (notificationListenerString != null && notificationListenerString.contains(
            packageName
        ))
    }
}

@Preview
@Composable
fun DefaultPreview() {
    RecentScreen()
}
