package com.lans.foodricion.presentation.screen.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.theme.*

@Composable
fun ChatScreen() {
    var userInput by remember { mutableStateOf("") }
    var chatMessages by remember { mutableStateOf(listOf(
        Pair("Hello! How can I help you today?", false),
        Pair("You can ask me anything about food and nutrition.", false),
        Pair("Nutrition Prediction", true) // User message example
    )) }

    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryContainer
            ),
            shape = RectangleShape // No radius
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user_small),
                    contentDescription = "Chatbot Profile Picture",
                    modifier = Modifier
                        .size(48.dp)
                        .background(color = Color.Gray, shape = CircleShape)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "FoodBudy",
                        color = Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Your Personal Assistant",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp), // Added vertical padding
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            chatMessages.forEach { (message, isUser) ->
                val backgroundColor = if (isUser) Color(0xFF85CCBF) else Color.LightGray
                val alignment = if (isUser) Arrangement.End else Arrangement.Start
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = alignment
                ) {
                    Text(
                        text = message,
                        color = Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .background(backgroundColor, shape = MaterialTheme.shapes.medium)
                            .padding(8.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFF85CCBF), shape = MaterialTheme.shapes.medium)
                    .padding(8.dp),
                textStyle = TextStyle(color = Black, fontSize = 16.sp)
            )
            IconButton(onClick = {
                if (userInput.isNotBlank()) {
                    chatMessages = chatMessages + Pair(userInput, true)
                    userInput = ""
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "Send",
                    tint = Test
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    ChatScreen()
}
