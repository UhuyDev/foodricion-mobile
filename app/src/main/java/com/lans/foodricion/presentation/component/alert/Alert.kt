package com.lans.foodricion.presentation.component.alert

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Alert(
    title: String,
    description: String,
    onDismissRequest: () -> Unit,
    onConfirmClick: @Composable () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Text(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                text = description
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = onConfirmClick
    )
}