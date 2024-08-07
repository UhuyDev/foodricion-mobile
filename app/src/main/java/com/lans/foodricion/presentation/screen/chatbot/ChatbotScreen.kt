package com.lans.foodricion.presentation.screen.chatbot

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.R
import com.lans.foodricion.domain.model.InputWrapper
import com.lans.foodricion.presentation.component.message_bubble.MessageBubble
import com.lans.foodricion.presentation.component.textfield.ValidableTextField
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.White

@Composable
fun ChatBotScreen(
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .background(Primary)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .clickable { },
                painter = painterResource(id = R.drawable.ic_back),
                tint = White,
                contentDescription = stringResource(id = R.string.content_description)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = stringResource(R.string.food_buddy),
                    color = White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.your_personal_assistant),
                    color = White,
                    fontSize = 14.sp
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                )
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ValidableTextField(
                modifier = Modifier
                    .padding(
                        end = 4.dp
                    )
                    .weight(1f),
                input = InputWrapper(""),
                isSupportiveText = false,
                onValueChange = {

                }
            )
            IconButton(
                modifier = Modifier
                    .size(48.dp),
                onClick = { }
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.ic_send),
                    tint = Primary,
                    contentDescription = stringResource(id = R.string.content_description)
                )
            }
        }
    }
}
