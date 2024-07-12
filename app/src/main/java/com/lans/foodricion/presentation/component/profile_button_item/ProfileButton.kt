package com.lans.foodricion.presentation.component.profile_button_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.theme.Danger
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.White

@Composable
fun ProfileButton(
    modifier: Modifier,
    text: String,
    color: Color = Primary,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedMedium
            )
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
            .clickable {
                onClick.invoke()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
        Icon(
            modifier = Modifier
                .size(16.dp),
            painter = painterResource(id = R.drawable.ic_right),
            tint = White,
            contentDescription = stringResource(R.string.content_description),
        )
    }
}
