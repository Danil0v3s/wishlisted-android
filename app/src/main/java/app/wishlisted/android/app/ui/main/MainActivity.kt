package app.wishlisted.android.app.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.wishlisted.android.app.ui.home.HomeScreen
import app.wishlisted.android.app.ui.home.deals.DealsViewModel
import app.wishlisted.android.app.ui.launcher.LauncherScreen
import app.wishlisted.android.app.ui.pdp.PdpScreen
import app.wishlisted.android.domain.model.Game
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

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
                    val viewModel = hiltViewModel<DealsViewModel>(backStackEntry = it)
                    HomeScreen(viewModel) { game ->
                        navController.putArgument(NavigationScreens.Pdp.Args.game, game)
                        navController.navigate(NavigationScreens.Pdp.route)
                    }
                }

                composable(NavigationScreens.Pdp.route) {
                    val game =
                        navController.getArgument<Game>(NavigationScreens.Pdp.Args.game)
                    PdpScreen(game)
                }
            }
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
    object Main : NavigationScreens("main")

    object Home : NavigationScreens("home")
    object Pdp : NavigationScreens("pdp") {
        object Args {
            const val game = "GAME"
        }
    }
}
