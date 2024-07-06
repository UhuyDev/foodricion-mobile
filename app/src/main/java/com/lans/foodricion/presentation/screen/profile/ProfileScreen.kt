package com.lans.foodricion.presentation.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.theme.*

@Composable
fun ProfileScreen(username: String?) {
    val isGuest = username == null
    val currentUsername by remember { mutableStateOf(username ?: "Guest") }
    val dailyFoods by remember { mutableStateOf(0) }
    val weight by remember { mutableStateOf(0.0) }
    val randomStatus = listOf("Active", "Busy", "Sleeping", "Eating").random()

    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD) // Light blue background for the card
            ),
            shape = RoundedCornerShape(15.dp) // Radius 15.dp for a softer corner
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Row for Settings Icon
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = "Settings",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                // Handle settings click
                            }
                    )
                }
                // Row for Profile Picture, Username, and Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user_medium),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(64.dp)
                            .background(color = Color.Gray, shape = CircleShape)
                            .padding(16.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = currentUsername,
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = randomStatus,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp)) // Additional padding between second and third rows
                // Row for Daily Foods and Weight
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(5.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Daily Foods",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "$dailyFoods",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp)) // Space between the columns
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(5.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Body Weight",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${weight}kg",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pen),
                                contentDescription = "Edit Weight",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
        if (isGuest) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Have an account? Sign in here",
                color = Color.Blue,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    // Handle sign-in click
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(username = null)
}
