package com.lans.foodricion.presentation.screen.edit_profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.alert.Alert
import com.lans.foodricion.presentation.component.button.LoadingButton
import com.lans.foodricion.presentation.component.textfield.ValidableTextField
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Danger

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    navigateToProfile: () -> Unit,
    fullname: String,
    email: String,
    age: String,
    height: String,
    weight: String
) {
    val context = LocalContext.current
    val state by viewModel.state
    var showAlert by remember {
        mutableStateOf(Pair(false, ""))
    }

    if (showAlert.first) {
        Alert(title = "Error", description = showAlert.second, onDismissClick = {
            showAlert = showAlert.copy(first = false)
        }, onConfirmClick = {
            Button(onClick = {
                showAlert = showAlert.copy(first = false)
            }) {
                Text(text = "Close")
            }
        })
    }

    LaunchedEffect(Unit) {
        state.fullname.value = fullname
        state.email.value = email
        state.age.value = age
        state.height.value = height
        state.weight.value = weight
    }

    LaunchedEffect(key1 = state.isSuccess, key2 = state.error) {
        if (state.isSuccess) {
            Toast.makeText(context, "Profile updated", Toast.LENGTH_SHORT).show()
            state.isSuccess = false
            navigateToProfile.invoke()
        }

        if (state.error.isNotBlank()) {
            showAlert = Pair(true, state.error)
            state.error = ""
        }
    }

    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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
                        .padding(
                            start = 16.dp
                        )
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
                    text = stringResource(R.string.edit_profile),
                    color = Black,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.size(48.dp)
                )
            }
            Box(
                modifier = Modifier.padding(
                    vertical = 16.dp
                ), contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = stringResource(id = R.string.content_description)
                )
            }
            ValidableTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                ),
                input = state.fullname,
                label = stringResource(R.string.name),
                onValueChange = {
                    viewModel.onEvent(EditProfileUIEvent.FullnameChanged(it))
                })
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            ValidableTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                ),
                input = state.email,
                label = stringResource(R.string.email),
                onValueChange = {
                    viewModel.onEvent(EditProfileUIEvent.EmailChanged(it))
                })
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            ValidableTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                ),
                input = state.age,
                label = stringResource(R.string.age),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    viewModel.onEvent(EditProfileUIEvent.AgeChanged(it))
                })
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            ValidableTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                ),
                input = state.height,
                label = stringResource(R.string.height),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    viewModel.onEvent(EditProfileUIEvent.HeightChanged(it))
                })
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            ValidableTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                ),
                input = state.weight,
                label = stringResource(R.string.weight),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    viewModel.onEvent(EditProfileUIEvent.WeightChanged(it))
                })
            LoadingButton(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(
                    horizontal = 24.dp
                ), text = stringResource(R.string.update), isLoading = false, onClick = {
                viewModel.onEvent(EditProfileUIEvent.SubmitButtonClicked)
            })
        }
        Column(
            modifier = Modifier.padding(
                bottom = 16.dp
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.delete_my_account),
                color = Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.this_action_cannot_be_undone_and_you_will_lose_all_your_data_and_history_permanently),
                color = Black,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            LoadingButton(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(
                    horizontal = 16.dp
                ),
                text = stringResource(R.string.delete),
                containerColor = Danger,
                isLoading = false,
                onClick = {
                    viewModel.onEvent(EditProfileUIEvent.DeleteButtonClicked)
                })
        }
    }
}


