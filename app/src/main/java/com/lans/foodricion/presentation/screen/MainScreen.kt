package com.lans.foodricion.presentation.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lans.foodricion.presentation.component.bottom_navigation.BottomNavigationBar
import com.lans.foodricion.presentation.navigation.AuthRoute
import com.lans.foodricion.presentation.navigation.MainRoute
import com.lans.foodricion.presentation.navigation.graph.MainNavGraph

@Composable
fun MainScreen(
    rootNavController: NavHostController,
    isAuthenticated: Boolean,
) {
    val mainNavController = rememberNavController()
    val activity = LocalContext.current as Activity
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    var lastSelectedRoute by remember { mutableStateOf<MainRoute>(MainRoute.HomeScreen) }

    BackHandler {
        if (currentRoute?.route == MainRoute.HomeScreen.route) {
            activity.finish()
        }
    }
    Scaffold(
        bottomBar = {
            if (currentRoute?.route != MainRoute.ChatBotScreen.route) {
                BottomNavigationBar(
                    selectedItem = lastSelectedRoute,
                    navigateToHome = {
                        mainNavController.navigate(MainRoute.HomeScreen.route) {
                            popUpTo(route = MainRoute.HomeScreen.route) {
                                inclusive = true
                            }
                        }
                        lastSelectedRoute = MainRoute.HomeScreen
                    },
                    navigateToAskBot = {
                        mainNavController.navigate(MainRoute.ChatBotScreen.route)
                    },
                    navigateToProfile = {
                        mainNavController.navigate(MainRoute.ProfileScreen.route) {
                            popUpTo(route = MainRoute.ProfileScreen.route) {
                                inclusive = true
                            }
                        }
                        lastSelectedRoute = MainRoute.ProfileScreen
                    },
                    onItemSelected = { route ->
                        lastSelectedRoute = route
                    }
                )
            }
        }
    ) { paddingValues ->
        MainNavGraph(
            rootNavController = rootNavController,
            mainNavController = mainNavController,
            innerPadding = paddingValues,
            isAuthenticated = isAuthenticated
        )
    }
}