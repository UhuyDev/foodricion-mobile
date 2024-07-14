package com.lans.foodricion.presentation.navigation.graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lans.foodricion.presentation.navigation.MainRoute
import com.lans.foodricion.presentation.screen.chatbot.ChatBotScreen
import com.lans.foodricion.presentation.screen.home.HomeScreen
import com.lans.foodricion.presentation.screen.profile.ProfileScreen

@Composable
fun MainNavGraph(
    rootNavController: NavController,
    mainNavController: NavHostController,
    lastRoute: MainRoute,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = mainNavController,
        startDestination = MainRoute.HomeScreen.route
    ) {
        composable(route = MainRoute.HomeScreen.route) {
            HomeScreen(innerPadding = innerPadding)
        }
        composable(
            route = MainRoute.ChatBotScreen.route,
            enterTransition = {
                when (initialState.destination.route) {
                    "details" ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )

                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    "details" ->
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )

                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    "details" ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )

                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    "details" ->
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )

                    else -> null
                }
            }
        ) {
            ChatBotScreen(
                innerPadding = innerPadding,
                navigateBack = {
                    mainNavController.navigateUp()
                }
            )
        }
        composable(route = MainRoute.ProfileScreen.route) {
            ProfileScreen(innerPadding = innerPadding)
        }
    }
}