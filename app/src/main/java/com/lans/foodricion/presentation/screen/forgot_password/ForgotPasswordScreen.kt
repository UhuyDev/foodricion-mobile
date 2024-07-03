package com.lans.foodricion.presentation.screen.forgot_password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.text.style.TextAlign
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
import kotlinx.coroutines.delay

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {
    val state by viewModel.state
    var showSuccess by remember {
        mutableStateOf(Pair(false, ""))
    }
    var showAlert by remember {
        mutableStateOf(Pair(false, ""))
    }

    if (showAlert.first) {
        Alert(
            title = "Error",
            description = showAlert.second,
            onDismissRequest = {
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

    if (showSuccess.first) {
        Alert(
            title = showSuccess.second,
            description = "",
            onDismissRequest = {
                showSuccess = showSuccess.copy(first = false)
            },
            onConfirmClick = {
                Button(onClick = {
                    showSuccess = showSuccess.copy(first = false)
                }) {
                    Text(text = "Close")
                }
            }
        )
    }

    LaunchedEffect(
        key1 = state.isOTPSent,
        key2 = state.error,
        key3 = state.isSendCodeClicked
    ) {
        val clicked = state.isSendCodeClicked
        val otpSent = state.isOTPSent
        val error = state.error

        if (clicked) {
            state.remainingTime = 60
            while (state.remainingTime > 0) {
                delay(1000L)
                state.remainingTime -= 1
            }
            state.isSendCodeClicked = false
        }

        if (otpSent) {
            showSuccess = showSuccess.copy(first = true, second = "OTP code sent")
        }

        if (error.isNotBlank()) {
            showAlert = Pair(true, error)
            state.error = ""
        }
    }

    LaunchedEffect(
        key1 = state.isSuccess
    ) {
        val success = state.isSuccess

        if (success) {
            showSuccess = showSuccess.copy(first = true, second = "Reset password success")
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
                text = stringResource(R.string.forgot_password),
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
                text = stringResource(R.string.please_enter_your_email_to_reset_password),
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
                    viewModel.onEvent(ForgotPasswordUIEvent.EmailChanged(it))
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
                input = state.otp,
                label = stringResource(R.string.verification_code),
                onValueChange = {
                    viewModel.onEvent(ForgotPasswordUIEvent.OTPChanged(it))
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
                input = state.newPassword,
                label = stringResource(R.string.new_password),
                isPassword = true,
                onValueChange = {
                    viewModel.onEvent(ForgotPasswordUIEvent.NewPasswordChanged(it))
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 28.dp,
                    ),
                text = if (state.isOTPSent) "Resend Code in ${state.remainingTime} s" else stringResource(
                    R.string.send_code
                ),
                isEnable = !state.isOTPSent,
                color = Secondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                onClick = {
                    viewModel.onEvent(ForgotPasswordUIEvent.SendCodeButtonClicked)
                }
            )
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
                text = stringResource(R.string.submit),
                isLoading = state.isLoading,
                onClick = {
                    viewModel.onEvent(ForgotPasswordUIEvent.SubmitButtonClicked)
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
                text = stringResource(R.string.back),
                onClick = {
                    navigateToSignIn.invoke()
                }
            )
        }
    }
}