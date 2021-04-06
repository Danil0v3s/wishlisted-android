package app.wishlisted.android.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import app.wishlisted.android.app.launcher.LauncherScreen
import app.wishlisted.android.app.notifications.recent.RecentScreen
import app.wishlisted.android.app.notifications.recent.RecentViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
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
                                }
                            )
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
}
