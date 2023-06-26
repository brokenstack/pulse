package pw.alk.pulse.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pw.alk.pulse.ui.screens.SharedViewModel
import pw.alk.pulse.ui.screens.comments.CommentsScreen
import pw.alk.pulse.ui.screens.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Comments : Screen("comments")
}

@Composable
fun HomeNavGraph (navController: NavHostController) {
    val sharedViewModel = SharedViewModel()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(navController, sharedViewModel)
        }

        composable(route = Screen.Comments.route) {
            CommentsScreen(navController, sharedViewModel)
        }
    }
}