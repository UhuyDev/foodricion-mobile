package com.lans.foodricion.presentation.component.unauthenticated_message

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.button.LoadingButton
import com.lans.foodricion.presentation.theme.Black

@Composable
fun UnauthenticatedMessage(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .width(200.dp),
            text = "You haven't signed in yet, let's sign in first",
            textAlign = TextAlign.Center,
            color = Black,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        LoadingButton(
            modifier = Modifier
                .width(200.dp),
            text = stringResource(id = R.string.sign_in),
            isLoading = false,
            onClick = onClick
        )
    }
}