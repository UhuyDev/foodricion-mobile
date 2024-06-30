package com.lans.foodricion.presentation.navigation

object NavGraph {
    const val RootGraph = "rootGraph"
    const val AuthGraph = "authGraph"
    const val MainGraph = "mainGraph"
}

sealed class AuthRoute(val route: String) {
    object SignInScreen: AuthRoute("signIn")
    object SignUpScreen: AuthRoute("signUp")
    object ForgotScreen: AuthRoute("forgot")
}

sealed class MainRoute(val route: String) {
    object HomeScreen: MainRoute("home")
    object AskBotScreen: MainRoute("askBot")
    object ProfileScreen: MainRoute("profile")
}