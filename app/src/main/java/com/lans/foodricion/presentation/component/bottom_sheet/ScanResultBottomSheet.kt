package com.lans.foodricion.presentation.component.bottom_sheet

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lans.foodricion.R
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.White

@Composable
fun ScanResultBottomSheet(
    modifier: Modifier,
    imgUri: Uri,
    result: String,
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
            Image(
                modifier = Modifier
                    .padding(16.dp, 8.dp),
                painter = rememberAsyncImagePainter(imgUri),
                contentDescription = stringResource(id = R.string.content_description)
            )

            Text(text = result)
        }
    }
}

@Composable
fun ScanResultBottomSheet(
    modifier: Modifier,
    imgUrl: String,
    result: String,
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
            Image(
                modifier = Modifier
                    .padding(16.dp, 8.dp),
                painter = rememberAsyncImagePainter(imgUrl),
                contentDescription = stringResource(id = R.string.content_description)
            )

            Text(text = result)
        }
    }
}