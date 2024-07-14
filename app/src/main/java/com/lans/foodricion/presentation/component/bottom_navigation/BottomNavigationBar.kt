package com.lans.foodricion.presentation.component.bottom_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.navigation.MainRoute
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Neutral
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.utils.topBorder

@Composable
fun BottomNavigationBar(
    selectedItem: MainRoute,
    navigateToHome: () -> Unit,
    navigateToAskBot: () -> Unit,
    navigateToProfile: () -> Unit,
    onItemSelected: (MainRoute) -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .height(100.dp)
            .topBorder(2.dp, Black),
        containerColor = Background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavigationItem().items().forEach { item ->
                IconButton(
                    onClick = {
                        when (item.route) {
                            is MainRoute.HomeScreen -> {
                                if (selectedItem != MainRoute.HomeScreen) {
                                    navigateToHome()
                                }
                            }

                            is MainRoute.ChatBotScreen -> {
                                if (selectedItem != MainRoute.ChatBotScreen) {
                                    navigateToAskBot()
                                }
                            }

                            is MainRoute.ProfileScreen -> {
                                if (selectedItem != MainRoute.ProfileScreen) {
                                    navigateToProfile()
                                }
                            }
                        }
                        if(item.route != MainRoute.ChatBotScreen) {
                            onItemSelected(item.route)
                        }
                    }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            painter = painterResource(id = item.icon),
                            tint = if (item.route == selectedItem) {
                                Primary
                            } else {
                                Neutral
                            },
                            contentDescription = stringResource(R.string.content_description)
                        )
                    }
                }
            }
        }
    }
}