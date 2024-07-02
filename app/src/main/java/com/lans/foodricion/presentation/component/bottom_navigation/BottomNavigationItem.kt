package com.lans.foodricion.presentation.component.bottom_navigation

import com.lans.foodricion.R
import com.lans.foodricion.presentation.navigation.MainRoute

data class BottomNavigationItem(
    val title: String = "",
    val route: MainRoute = MainRoute.HomeScreen,
    val icon: Int = 0,
) {
    fun items(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                title = "Home",
                route = MainRoute.HomeScreen,
                icon = R.drawable.ic_home
            ),
            BottomNavigationItem(
                title = "Ask Bot",
                route = MainRoute.AskBotScreen,
                icon = R.drawable.ic_chat
            ),
            BottomNavigationItem(
                title = "Profile",
                route = MainRoute.ProfileScreen,
                icon = R.drawable.ic_profile
            ),
        )
    }
}
