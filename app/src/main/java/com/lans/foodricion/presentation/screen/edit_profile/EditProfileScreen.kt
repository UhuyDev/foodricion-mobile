package com.lans.foodricion.presentation.screen.edit_profile

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.R
import com.lans.foodricion.domain.model.InputWrapper
import com.lans.foodricion.presentation.component.button.LoadingButton
import com.lans.foodricion.presentation.component.textfield.ValidableTextField
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Danger

@Composable
fun EditProfileScreen(
//    viewModel: EditProfileViewModel = hiltViewModel(),
//    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
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
                        .clickable { },
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
                modifier = Modifier
                    .padding(
                        vertical = 16.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = stringResource(id = R.string.content_description)
                )
            }
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
        Column(
            modifier = Modifier
                .padding(
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
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
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(
                        horizontal = 16.dp
                    ),
                text = stringResource(R.string.delete),
                containerColor = Danger,
                isLoading = false,
                onClick = { }
            )
        }
    }
}

@Preview
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen()
}


