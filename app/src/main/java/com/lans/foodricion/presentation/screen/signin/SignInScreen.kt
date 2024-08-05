    package com.lans.foodricion.presentation.screen.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.alert.Alert
import com.lans.foodricion.presentation.component.button.GhostButton
import com.lans.foodricion.presentation.component.button.LoadingButton
import com.lans.foodricion.presentation.component.button.TextButton
import com.lans.foodricion.presentation.component.textfield.ValidableTextField
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Secondary

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToHome: () -> Unit
) {
    val state by viewModel.state
    var showAlert by remember { mutableStateOf(Pair(false, "")) }

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

    LaunchedEffect(key1 = state.isLoggedIn, key2 = state.error) {
        val response = state.isLoggedIn
        val error = state.error

        if (response) {
            navigateToHome.invoke()
        }

        if (error.isNotBlank()) {
            showAlert = Pair(true, error)
            state.error = ""
        }
    }

    Column(
        modifier = Modifier
            .background(Background)
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(0.92f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        top = 36.dp,
                        end = 24.dp
                    ),
                text = stringResource(R.string.sign_in),
                color = Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    ),
                text = stringResource(R.string.hello_welcome_back),
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            ValidableTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    ),
                input = state.email,
                label = stringResource(R.string.email),
                onValueChange = {
                    viewModel.onEvent(SignInUIEvent.EmailChanged(it))
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            ValidableTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    ),
                input = state.password,
                isPassword = true,
                label = stringResource(R.string.password),
                onValueChange = {
                    viewModel.onEvent(SignInUIEvent.PasswordChanged(it))
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    modifier = Modifier
                        .padding(
                            start = 28.dp,
                            top = 4.dp,
                            end = 28.dp,
                            bottom = 4.dp
                        ),
                    text = stringResource(R.string.forgot_password),
                    color = Secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    onClick = {
                        navigateToForgotPassword.invoke()
                    }
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(
                        horizontal = 24.dp
                    ),
                text = stringResource(R.string.sign_in),
                isLoading = state.isLoading,
                onClick = {
                    viewModel.onEvent(SignInUIEvent.SignInButtonClicked)
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            GhostButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(
                        horizontal = 24.dp
                    ),
                text = stringResource(R.string.continue_as_guest),
                onClick = {
                    navigateToHome.invoke()
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.08f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.don_t_have_an_account),
                color = Black,
                fontSize = 14.sp,
            )
            Spacer(
                modifier = Modifier
                    .width(4.dp)
            )
            TextButton(
                modifier = Modifier,
                text = stringResource(R.string.sign_up),
                color = Secondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                onClick = {
                    navigateToSignUp.invoke()
                }
            )
        }
    }
}