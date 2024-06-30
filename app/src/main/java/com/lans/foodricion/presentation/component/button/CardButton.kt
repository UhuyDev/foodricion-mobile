package com.lans.foodricion.presentation.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.PrimaryContainer
import com.lans.foodricion.presentation.theme.White

@Composable
fun CardButton(
    modifier: Modifier,
    containerColor: Color = PrimaryContainer,
    icon: Painter,
    iconColor: Color = White,
    text: String,
    textColor: Color = Black,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .size(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .weight(0.7f),
                painter = icon,
                colorFilter = ColorFilter.tint(color = iconColor),
                contentDescription = stringResource(id = R.string.content_description)
            )
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(0.3f),
                text = text,
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}