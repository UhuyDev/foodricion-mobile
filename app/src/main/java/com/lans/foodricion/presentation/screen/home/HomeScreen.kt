package com.lans.foodricion.presentation.screen.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.alert.CameraPermissionTextProvider
import com.lans.foodricion.presentation.component.alert.PermissionAlert
import com.lans.foodricion.presentation.component.bottom_sheet.ScanBottomSheet
import com.lans.foodricion.presentation.component.bottom_sheet.ScanResultBottomSheet
import com.lans.foodricion.presentation.component.button.CardButton
import com.lans.foodricion.presentation.component.daily_nutrition.DailyNutrition
import com.lans.foodricion.presentation.component.nutrition_history.NutritionHistoryItem
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.PrimaryContainer
import com.lans.foodricion.utils.getActivity

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    isAuthenticated: Boolean
) {
    val context = LocalContext.current
    val state by viewModel.state
    var showPermissionAlert by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }

    val permission = Manifest.permission.CAMERA
    val authority = stringResource(id = R.string.file_provider)
    val takePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            state.tempUri = state.tempUri
            state.rotation = 0
            state.classifierResult = viewModel.classify(
                MediaStore.Images.Media.getBitmap(context.contentResolver, state.tempUri),
                state.rotation
            )
            showResult = true
        }
    )
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            state.tempUri = it!!
            state.rotation = 0
            state.classifierResult = viewModel.classify(
                MediaStore.Images.Media.getBitmap(context.contentResolver, state.tempUri),
                state.rotation
            )
            showResult = true
        }
    )
    val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                viewModel.getTempUri(authority = authority)
                showBottomSheet = true
            } else {
                showPermissionAlert = true
            }
        }
    )

    if (showResult) {
        ScanResultBottomSheet(
            modifier = Modifier,
            imgUri = state.tempUri,
            result = state.classifierResult.toString(),
            onDismissClick = {
                showResult = false
                state.classifierResult = emptyList()
            }
        )
    }

    if (showPermissionAlert) {
        PermissionAlert(
            permissionTextProvider = CameraPermissionTextProvider(),
            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                context.getActivity()!!,
                permission
            ),
            onDismissClick = {
                showPermissionAlert = false
            },
            onOkClick = {
                showPermissionAlert = false
                cameraPermissionResultLauncher.launch(permission)
            },
            onGoToAppSettingsClick = {
                val activity = context.getActivity()
                activity!!.openAppSettings()
            }
        )
    }

    if (showBottomSheet) {
        ScanBottomSheet(
            modifier = Modifier,
            onDismissClick = {
                showBottomSheet = false
            },
            onTakePhotoClick = {
                showBottomSheet = false
                if (ContextCompat.checkSelfPermission(
                        context,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val uri = state.tempUri
                    takePhotoLauncher.launch(uri)
                } else {
                    cameraPermissionResultLauncher.launch(permission)
                }
            },
            onPhotoGalleryClick = {
                showBottomSheet = false
                imagePickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            })
    }

    Column(
        modifier = Modifier
            .background(Background)
            .statusBarsPadding()
            .padding(
                top = 8.dp,
                bottom = innerPadding.calculateBottomPadding()
            )
    ) {
        DailyNutrition(
            modifier = Modifier,
            calorieValue = 200f,
            calorieMaxValue = 1800f,
            proteinValue = 200f,
            proteinMaxValue = 1800f,
            carboValue = 200f,
            carboMaxValue = 1800f,
            fiberValue = 200f,
            fiberMaxValue = 1800f,
            fatValue = 200f,
            fatMaxValue = 1800f
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardButton(
                modifier = Modifier,
                icon = painterResource(id = R.drawable.ic_scan),
                iconColor = Primary,
                text = stringResource(R.string.scan),
                onClick = {
                    cameraPermissionResultLauncher.launch(permission)
                }
            )
            CardButton(
                modifier = Modifier,
                icon = painterResource(id = R.drawable.ic_apple),
                iconColor = Primary,
                text = stringResource(R.string.foods),
                onClick = {
                }
            )
            CardButton(
                modifier = Modifier,
                icon = painterResource(id = R.drawable.ic_calculator),
                iconColor = Primary,
                text = stringResource(R.string.bmi),
                onClick = { }
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp
                )
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryContainer
            )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        bottom = 12.dp
                    ),
                text = stringResource(R.string._29_jan_2024),
                color = Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
            }
        }
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}