package com.lans.foodricion.presentation.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lans.foodricion.presentation.component.bottom_navigation.BottomNavigationBar
import com.lans.foodricion.presentation.navigation.MainRoute
import com.lans.foodricion.presentation.navigation.graph.MainNavGraph

@Composable
fun MainScreen(
    rootNavController: NavHostController
) {
    val mainNavController = rememberNavController()
    val activity = LocalContext.current as Activity
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    BackHandler {
        if (currentRoute?.route == MainRoute.HomeScreen.route) {
            activity.finish()
        }
    }
    Scaffold(
        bottomBar = {
            if (currentRoute?.route != MainRoute.ChatBotScreen.route) {
                BottomNavigationBar(
                    navigateToHome = { mainNavController.navigate(MainRoute.HomeScreen.route) },
                    navigateToAskBot = { mainNavController.navigate(MainRoute.ChatBotScreen.route) },
                    navigateToProfile = { mainNavController.navigate(MainRoute.ProfileScreen.route) }
                )
            }
        }
    ) { paddingValues ->
        MainNavGraph(
            rootNavController = rootNavController,
            mainNavController = mainNavController,
            innerPadding = paddingValues
        )
    }
}