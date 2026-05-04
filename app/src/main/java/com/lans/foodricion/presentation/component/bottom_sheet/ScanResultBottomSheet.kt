package com.lans.foodricion.presentation.component.bottom_sheet

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.button.LoadingButton
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Neutral
import com.lans.foodricion.presentation.theme.White

@Composable
fun ScanResultBottomSheet(
    modifier: Modifier,
    imgUri: Uri,
    result: String,
    onCheckNutritionClick: () -> Unit,
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
                text = result,
                textAlign = TextAlign.Center,
                color = Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
            )
            Image(
                modifier = Modifier
                    .background(Neutral)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter(imgUri),
                contentDescription = stringResource(id = R.string.content_description)
            )
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
            )
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.check_nutrition),
                isLoading = false,
                onClick = onCheckNutritionClick
            )
        }
    }
}

@Composable
fun ScanResultBottomSheet(
    modifier: Modifier,
    imgUrl: String,
    result: String,
    onCheckNutritionClick: () -> Unit,
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
                text = result,
                textAlign = TextAlign.Center,
                color = Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
            )
            Image(
                modifier = Modifier
                    .padding(16.dp, 8.dp),
                painter = rememberAsyncImagePainter(imgUrl),
                contentDescription = stringResource(id = R.string.content_description)
            )
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.check_nutrition),
                isLoading = false,
                onClick = onCheckNutritionClick
            )
        }
    }
}