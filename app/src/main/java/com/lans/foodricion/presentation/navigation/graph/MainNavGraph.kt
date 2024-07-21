package com.lans.foodricion.presentation.navigation.graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lans.foodricion.presentation.component.keyboard_aware.KeyboardAware
import com.lans.foodricion.presentation.navigation.AuthRoute
import com.lans.foodricion.presentation.navigation.MainRoute
import com.lans.foodricion.presentation.screen.change_password.ChangePasswordScreen
import com.lans.foodricion.presentation.screen.chatbot.ChatBotScreen
import com.lans.foodricion.presentation.screen.edit_profile.EditProfileScreen
import com.lans.foodricion.presentation.screen.food.FoodScreen
import com.lans.foodricion.presentation.screen.home.HomeScreen
import com.lans.foodricion.presentation.screen.profile.ProfileScreen

@Composable
fun MainNavGraph(
    rootNavController: NavController,
    mainNavController: NavHostController,
    innerPadding: PaddingValues,
    isAuthenticated: Boolean
) {
    NavHost(
        navController = mainNavController,
        startDestination = MainRoute.HomeScreen.route
    ) {
        composable(route = MainRoute.HomeScreen.route) {
            HomeScreen(
                innerPadding = innerPadding,
                navigateToFood = {
                    mainNavController.navigate(route = MainRoute.FoodScreen.route) {
                        popUpTo(route = MainRoute.HomeScreen.route)
                    }
                },
                isAuthenticated = isAuthenticated
            )
        }
        composable(
            route = MainRoute.ChatBotScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            }
        ) {
            ChatBotScreen(
                isAuthenticated = isAuthenticated,
                navigateToSignIn = {
                    rootNavController.navigate(route = AuthRoute.SignInScreen.route) {
                        popUpTo(route = MainRoute.ProfileScreen.route)
                    }
                },
                navigateBack = {
                    mainNavController.navigateUp()
                }
            )
        }
        composable(route = MainRoute.ProfileScreen.route) {
            KeyboardAware {
                ProfileScreen(
                    isAuthenticated = isAuthenticated,
                    navigateToSignIn = {
                        rootNavController.navigate(route = AuthRoute.SignInScreen.route) {
                            popUpTo(route = MainRoute.ProfileScreen.route)
                        }
                    },
                    navigateToSignUp = {
                        rootNavController.navigate(route = AuthRoute.SignUpScreen.route) {
                            popUpTo(route = MainRoute.ProfileScreen.route)
                        }
                    },
                    navigateToEditProfile = { fullname, email ->
                        mainNavController.navigate(route = MainRoute.EditProfileScreen.route + "/${fullname}/${email}") {
                            popUpTo(route = MainRoute.ProfileScreen.route)
                        }
                    },
                    navigateToChangePassword = {
                        mainNavController.navigate(route = MainRoute.ChangePasswordScreen.route) {
                            popUpTo(route = MainRoute.ProfileScreen.route)
                        }
                    },
                    signOut = {
                        rootNavController.navigate(route = AuthRoute.SignInScreen.route) {
                            popUpTo(route = MainRoute.ProfileScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable(route = MainRoute.EditProfileScreen.route + "/{fullname}/{email}") {
            val fullname = it.arguments?.getString("fullname") ?: ""
            val email = it.arguments?.getString("email") ?: ""
            EditProfileScreen(
                navigateToProfile = {
                    mainNavController.navigateUp()
                },
                fullname = fullname,
                email = email
            )
        }
        composable(route = MainRoute.ChangePasswordScreen.route) {
            ChangePasswordScreen(
                navigateToProfile = {
                    mainNavController.navigateUp()
                }
            )
        }
        composable(route = MainRoute.FoodScreen.route) {
            FoodScreen(
                navigateToHome = {
                    mainNavController.navigateUp()
                }
            )
        }
    }
}