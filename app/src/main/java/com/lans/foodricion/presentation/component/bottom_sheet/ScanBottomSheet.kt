package com.lans.foodricion.presentation.component.bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.presentation.component.button.TextButton
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanBottomSheet(
    modifier: Modifier,
    onTakePhotoClick: () -> Unit,
    onPhotoGalleryClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        containerColor = White,
        contentColor = Black,
        onDismissRequest = onDismissClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Choose",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp
                    ),
                text = "Take a picture",
                color = Black,
                fontSize = 16.sp,
                lineHeight = 48.sp,
                onClick = onTakePhotoClick
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp
                    ),
                text = "Choose picture from gallery",
                color = Black,
                fontSize = 16.sp,
                lineHeight = 48.sp,
                onClick = onPhotoGalleryClick
            )
        }
    }
}