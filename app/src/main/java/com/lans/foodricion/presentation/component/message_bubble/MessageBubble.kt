package com.lans.foodricion.presentation.component.message_bubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.Secondary

@Composable
fun MessageBubble(
    modifier: Modifier,
    message: String,
    isUser: Boolean,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp
            ),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Text(
            modifier = Modifier
                .background(
                    color = if (isUser) Primary else Secondary,
                    shape = RoundedMedium
                )
                .padding(8.dp),
            text = message,
            color = Black,
            fontSize = 16.sp,
        )
    }
}