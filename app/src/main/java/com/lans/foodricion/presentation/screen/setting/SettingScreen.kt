package com.lans.foodricion.presentation.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import com.lans.foodricion.R

@Composable
fun SettingScreen() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_button_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        // Handle back click
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Setting",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Scrollable content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                // Account Setting
                Text(
                    text = "Account Setting",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                SettingItem(text = "Account")
                SettingItem(text = "Change Password")
                SettingItem(text = "Notification", isSwitch = true)

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // More
                Text(
                    text = "More",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                SettingItem(text = "Language")
                SettingItem(text = "About Us")
                SettingItem(text = "Terms Condition")
                SettingItem(text = "Privacy and Policy")
                SettingItem(text = "Contact Us")
                SettingItem(text = "FAQ")

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Log Out
                SettingItem(text = "Log Out", isLogout = true)
            }
        }
    }
}

@Composable
fun SettingItem(text: String, isSwitch: Boolean = false, isLogout: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                if (isLogout) Color.Red else Color(0xFF66CC99),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                // Handle item click
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = if (isLogout) FontWeight.Bold else FontWeight.Normal
        )
        if (isSwitch) {
            Switch(
                checked = false,
                onCheckedChange = { /* Handle switch change */ },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.Gray,
                    checkedTrackColor = Color.DarkGray,
                    uncheckedTrackColor = Color.LightGray
                )
            )
        } else if (!isLogout) {
            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = "Arrow Right",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingScreen()
}
