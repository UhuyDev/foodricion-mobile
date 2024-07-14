package com.lans.foodricion.presentation.component.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.White

@Composable
fun LoadingButton(
    modifier: Modifier,
    text: String,
    containerColor: Color = Primary,
    contentColor: Color = White,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedMedium,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
        onClick = onClick
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp),
                color = contentColor
            )
        } else {
            Text(
                text = text,
                fontSize = 16.sp
            )
        }
    }
}