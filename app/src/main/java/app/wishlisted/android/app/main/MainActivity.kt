package app.wishlisted.android.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import app.wishlisted.android.app.home.deals.DealsScreen
import app.wishlisted.android.app.launcher.LauncherScreen
import app.wishlisted.android.app.pdp.PdpScreen
import app.wishlisted.android.domain.model.Game
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import java.lang.IllegalArgumentException

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
                        startDestination = NavigationScreens.Launcher.route
                    ) {

                        composable(NavigationScreens.Launcher.route) {
                            LauncherScreen(
                                state = viewModel.state.collectAsState(),
                                onLoadingFinished = {
                                    navController.navigate(NavigationScreens.Home.route) {
                                        popUpTo(NavigationScreens.Launcher.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }

                        composable(NavigationScreens.Home.route) {
                            DealsScreen(
                                onItemClick = { game ->
                                    navController.putArgument(NavigationScreens.Pdp.Args.game, game)
                                    navController.navigate(NavigationScreens.Pdp.route)
                                }
                            )
                        }

                        composable(NavigationScreens.Pdp.route) {
                            val game =
                                navController.getArgument<Game>(NavigationScreens.Pdp.Args.game)
                            PdpScreen(game)
                        }
                    }
                }
            )
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> NavHostController.getArgument(name: String): T {
    return previousBackStackEntry?.arguments?.getSerializable(name) as? T
        ?: throw IllegalArgumentException()
}

fun NavHostController.putArgument(name: String, arg: Serializable?) {
    currentBackStackEntry?.arguments?.putSerializable(name, arg)
}

sealed class NavigationScreens(
    val route: String
) {
    object Launcher : NavigationScreens("launcher")
    object Home : NavigationScreens("home")
    object Pdp : NavigationScreens("pdp") {
        object Args {
            const val game = "GAME"
        }
    }
}
