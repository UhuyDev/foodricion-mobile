package com.lans.foodricion.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lans.foodricion.presentation.navigation.AuthRoute
import com.lans.foodricion.presentation.navigation.NavGraph
import com.lans.foodricion.presentation.screen.forgot_password.ForgotPasswordScreen
import com.lans.foodricion.presentation.screen.signin.SignInScreen
import com.lans.foodricion.presentation.screen.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController
) {
    navigation(
        route = NavGraph.AuthGraph,
        startDestination = AuthRoute.SignInScreen.route
    ) {
        composable(route = AuthRoute.SignInScreen.route) {
            SignInScreen(
                navigateToSignUp = {
                    navController.navigate(route = AuthRoute.SignUpScreen.route) {
                        popUpTo(route = AuthRoute.SignInScreen.route)
                    }
                },
                navigateToForgotPassword = {
                    navController.navigate(route = AuthRoute.ForgotScreen.route) {
                        popUpTo(route = AuthRoute.ForgotScreen.route)
                    }
                },
                navigateToHome = {
                    navController.navigate(route = NavGraph.MainGraph) {
                        popUpTo(route = AuthRoute.SignInScreen.route)
                    }
                }
            )
        }
        composable(route = AuthRoute.SignUpScreen.route) {
            SignUpScreen(
                navigateToSignIn = {
                    navController.navigateUp()
                }
            )
        }
        composable(route = AuthRoute.ForgotScreen.route) {
            ForgotPasswordScreen(
                navigateToSignIn = {
                    navController.navigateUp()
                }
            )
        }
    }
}