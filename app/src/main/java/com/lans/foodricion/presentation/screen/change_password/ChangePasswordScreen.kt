package com.lans.foodricion.presentation.screen.change_password

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lans.foodricion.R
import com.lans.foodricion.domain.model.InputWrapper
import com.lans.foodricion.presentation.component.button.GhostButton
import com.lans.foodricion.presentation.component.button.LoadingButton
import com.lans.foodricion.presentation.component.button.TextButton
import com.lans.foodricion.presentation.component.textfield.ValidableTextField
import com.lans.foodricion.presentation.screen.edit_profile.EditProfileScreen
import com.lans.foodricion.presentation.screen.forgot_password.ForgotPasswordUIEvent
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Secondary

@Composable
fun ChangePasswordScreen(
    viewModel: ChangePasswordViewModel = hiltViewModel(),
    navigateToProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navigateToProfile.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_back),
                tint = Black,
                contentDescription = stringResource(id = R.string.content_description)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = stringResource(R.string.change_password),
                color = Black,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.size(48.dp)
            )
        }
        Spacer(
            modifier = Modifier.size(24.dp)
        )
        ValidableTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                ),
            input = InputWrapper(""),
            label = stringResource(R.string.name),
            onValueChange = { }
        )
        ValidableTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                ),
            input = InputWrapper(""),
            label = stringResource(R.string.email),
            onValueChange = { }
        )
        LoadingButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(
                    horizontal = 24.dp
                ),
            text = stringResource(R.string.update),
            isLoading = false,
            onClick = { }
        )
    }
}