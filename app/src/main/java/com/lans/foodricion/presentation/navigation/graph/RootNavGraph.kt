package com.lans.foodricion.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lans.foodricion.presentation.navigation.NavGraph
import com.lans.foodricion.presentation.screen.MainScreen
import com.lans.foodricion.presentation.screen.profile.ProfileScreen

@Composable
fun RootNavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = NavGraph.RootGraph,
        startDestination = startDestination
    ) {
        authNavGraph(navController = navController)
        composable(route = NavGraph.MainGraph) {
            MainScreen(rootNavController = navController)
        }
    }
}