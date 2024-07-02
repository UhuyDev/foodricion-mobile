package com.lans.foodricion.presentation.component.nutrition_history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Danger
import com.lans.foodricion.presentation.theme.PrimaryContainer
import com.lans.foodricion.presentation.theme.RoundedLarge
import com.lans.foodricion.presentation.theme.White

@Composable
fun NutritionHistoryItem(
    imgUrl: String,
    calorie: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryContainer
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .background(White)
                    .clip(RoundedLarge)
                    .size(50.dp),
                painter = painterResource(id = R.drawable.img_food),
                contentDescription = stringResource(id = R.string.content_description)
            )
            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )
            Column {
                Text(
                    text = stringResource(R.string.lorem_ipsum),
                    color = Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$calorie ccal",
                    color = Black,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    tint = Danger,
                    contentDescription = stringResource(id = R.string.content_description)
                )
            }
        }
    }
}