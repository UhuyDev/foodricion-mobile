package com.lans.foodricion.presentation.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.alert.Alert
import com.lans.foodricion.presentation.component.profile_button_item.ProfileButton
import com.lans.foodricion.presentation.component.shimmer.Shimmer
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Danger
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.PrimaryDark
import com.lans.foodricion.presentation.theme.RoundedLarge
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.White

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    isAuthenticated: Boolean,
    navigateToSignIn: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToEditProfile: (fullname: String, email: String) -> Unit,
    navigateToChangePassword: () -> Unit,
    signOut: () -> Unit
) {
    val state = viewModel.state.value
    var showAlert by remember { mutableStateOf(Pair(false, "")) }
    var showConfirmation by remember { mutableStateOf(false) }

    if (showAlert.first) {
        Alert(
            title = "Error",
            description = showAlert.second,
            onDismissClick = {
                showAlert = showAlert.copy(first = false)
            },
            onConfirmClick = {
                Button(onClick = {
                    showAlert = showAlert.copy(first = false)
                }) {
                    Text(text = "Close")
                }
            }
        )
    }

    if (showConfirmation) {
        Alert(
            title = "Sign Out",
            description = "Do you want to sign out?",
            onDismissClick = {
                showConfirmation = false
            },
            onConfirmClick = {
                Button(onClick = {
                    showConfirmation = false
                }) {
                    Text(text = "Close")
                }
            }
        )
    }

    LaunchedEffect(key1 = isAuthenticated) {
        if (isAuthenticated) {
            viewModel.getMe()
        }
    }

    LaunchedEffect(key1 = state.error) {
        if (!isAuthenticated) {
            val error = state.error

            if (error.isNotBlank()) {
                showAlert = Pair(true, state.error)
                state.error = ""
            }
        }
    }

    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    bottom = 24.dp
                ),
            text = stringResource(R.string.profile),
            color = Black,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        if (isAuthenticated) {
            Row(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        bottom = 16.dp,
                        end = 16.dp
                    )
                    .background(
                        color = Primary,
                        shape = RoundedMedium
                    )
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .background(
                            color = White,
                            shape = CircleShape
                        )
                        .size(52.dp),
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = stringResource(id = R.string.content_description)
                )
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .background(
                                brush = Shimmer(
                                    targetValue = 1300f,
                                    showShimmer = state.isLoading
                                ),
                                shape = RoundedLarge,
                                alpha = 0.8f
                            )
                            .fillMaxWidth(),
                        text = state.user?.fullname ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier
                            .background(
                                brush = Shimmer(
                                    targetValue = 1300f,
                                    showShimmer = state.isLoading
                                ),
                                shape = RoundedLarge,
                                alpha = 0.8f
                            )
                            .fillMaxWidth(),
                        text = state.user?.email ?: "",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                IconButton(
                    modifier = Modifier
                        .background(
                            color = PrimaryDark,
                            shape = CircleShape
                        ),
                    onClick = {
                        navigateToEditProfile.invoke(
                            state.user?.fullname ?: "",
                            state.user?.email ?: ""
                        )
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_pen),
                        contentDescription = stringResource(id = R.string.content_description),
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (!isAuthenticated) {
                ProfileButton(
                    modifier = Modifier,
                    text = stringResource(id = R.string.sign_in),
                    onClick = {
                        navigateToSignIn.invoke()
                    }
                )
                ProfileButton(
                    modifier = Modifier,
                    text = stringResource(id = R.string.sign_up),
                    onClick = {
                        navigateToSignUp.invoke()
                    }
                )
            }

            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.change_password),
                onClick = {
                    navigateToChangePassword.invoke()
                }
            )
            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.contact_us),
                onClick = {}
            )
            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.about),
                onClick = {}
            )

            if (isAuthenticated) {
                ProfileButton(
                    modifier = Modifier,
                    text = stringResource(R.string.sign_out),
                    color = Danger,
                    onClick = {
                        viewModel.onEvent(ProfileUIEvent.LogoutButtonClicked)
                        signOut.invoke()
                    }
                )
            }
        }
    }
}