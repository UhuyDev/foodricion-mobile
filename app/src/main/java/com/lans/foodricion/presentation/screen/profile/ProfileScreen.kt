package com.lans.foodricion.presentation.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.profile_button_item.ProfileButton
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Danger
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.PrimaryDark
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.White

@Composable
fun ProfileScreen() {
    val isLoggedIn = false
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
                    bottom = 24.dp
                ),
            text = stringResource(R.string.profile),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        if (isLoggedIn) {
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
                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.name_example),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(R.string.email_example),
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
                    onClick = {}
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
            if (!isLoggedIn) {
                ProfileButton(
                    modifier = Modifier,
                    text = stringResource(id = R.string.sign_in),
                    onClick = {}
                )
                ProfileButton(
                    modifier = Modifier,
                    text = "Sign Up",
                    onClick = {}
                )
            }

            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.change_password),
                onClick = {}
            )
            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.language),
                onClick = {}
            )
            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.about_us),
                onClick = {}
            )
            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.terms_condition),
                onClick = {}
            )
            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.privacy_and_policy),
                onClick = {}
            )
            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.contact_us),
                onClick = {}
            )
            ProfileButton(
                modifier = Modifier,
                text = stringResource(R.string.faq),
                onClick = {}
            )

            if (isLoggedIn) {
                ProfileButton(
                    modifier = Modifier,
                    text = stringResource(R.string.sign_out),
                    color = Danger,
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
