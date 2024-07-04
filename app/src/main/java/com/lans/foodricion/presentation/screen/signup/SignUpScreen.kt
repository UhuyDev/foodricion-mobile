package com.lans.foodricion.presentation.screen.signup

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.alert.Alert
import com.lans.foodricion.presentation.component.button.LoadingButton
import com.lans.foodricion.presentation.component.button.TextButton
import com.lans.foodricion.presentation.component.textfield.ValidableTextField
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Secondary

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {
    val context = LocalContext.current
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

    LaunchedEffect(key1 = state.signUpResponse, key2 = state.error) {
        val response = state.signUpResponse
        val error = state.error

        if (response) {
            navigateToSignIn()
            Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
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
            .navigationBarsPadding()
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
                text = stringResource(R.string.sign_up),
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
                text = stringResource(R.string.please_enter_your_credentials),
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
                input = state.fullname,
                label = stringResource(R.string.name),
                onValueChange = {
                    viewModel.onEvent(SignUpUIEvent.FullnameChanged(it))
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
                input = state.email,
                label = stringResource(R.string.email),
                onValueChange = {
                    viewModel.onEvent(SignUpUIEvent.EmailChanged(it))
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
                label = stringResource(R.string.password),
                isPassword = true,
                onValueChange = {
                    viewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
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
                input = state.confirmPassword,
                label = stringResource(R.string.confirm_password),
                isPassword = true,
                onValueChange = {
                    viewModel.onEvent(SignUpUIEvent.ConfirmPasswordChanged(it))
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(
                        horizontal = 24.dp
                    ),
                text = "Sign In",
                isLoading = state.isLoading,
                onClick = {
                    viewModel.onEvent(SignUpUIEvent.SignUpButtonClicked)
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
                text = stringResource(R.string.have_an_account_already),
                color = Black,
                fontSize = 14.sp
            )
            Spacer(
                modifier = Modifier
                    .width(4.dp)
            )
            TextButton(
                modifier = Modifier,
                text = stringResource(R.string.sign_in),
                color = Secondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                onClick = {
                    navigateToSignIn.invoke()
                }
            )
        }
    }
}