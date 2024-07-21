package com.lans.foodricion.presentation.component.food_item

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
import coil.compose.AsyncImage
import com.lans.foodricion.R
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Danger
import com.lans.foodricion.presentation.theme.PrimaryContainer
import com.lans.foodricion.presentation.theme.RoundedLarge
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.Success
import com.lans.foodricion.presentation.theme.White

@Composable
fun FoodItem(
    modifier: Modifier,
    imgUrl: String,
    foodName: String = "",
    calorie: Int = 0,
    isHistory: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
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
            AsyncImage(
                modifier = Modifier
                    .background(
                        color = White,
                        shape = RoundedMedium
                    )
                    .clip(RoundedLarge)
                    .size(50.dp),
                model = imgUrl,
                error = painterResource(id = R.drawable.img_food),
                contentDescription = stringResource(R.string.content_description)
            )
            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )
            Column {
                Text(
                    text = foodName,
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
                onClick = onClick
            ) {
                Icon(
                    painter = if (isHistory) painterResource(id = R.drawable.ic_minus) else painterResource(
                        id = R.drawable.ic_circle_plus
                    ),
                    tint = if (isHistory) Danger else Success,
                    contentDescription = stringResource(id = R.string.content_description)
                )
            }
        }
    }
}