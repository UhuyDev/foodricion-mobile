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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.lans.foodricion.presentation.component.alert.Alert
import com.lans.foodricion.presentation.component.alert.CameraPermissionTextProvider
import com.lans.foodricion.presentation.component.alert.PermissionAlert
import com.lans.foodricion.presentation.component.bottom_sheet.ScanBottomSheet
import com.lans.foodricion.presentation.component.bottom_sheet.ScanResultBottomSheet
import com.lans.foodricion.presentation.component.button.CardButton
import com.lans.foodricion.presentation.component.daily_nutrition.DailyNutrition
import com.lans.foodricion.presentation.component.food_item.FoodItem
import com.lans.foodricion.presentation.component.unauthenticated_message.UnauthenticatedMessage
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Neutral
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.PrimaryContainer
import com.lans.foodricion.utils.getActivity
import com.lans.foodricion.utils.getMinimumNutrition
import java.util.Locale

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    navigateToSignIn: () -> Unit,
    navigateToEditProfile: (fullname: String, email: String, age: String, height: String, weight: String) -> Unit,
    navigateToFood: () -> Unit,
    navigateToFoodDetail: (foodName: String) -> Unit,
    navigateToBMI: () -> Unit,
    isAuthenticated: Boolean
) {
    val context = LocalContext.current
    val state by viewModel.state
    var showPermissionAlert by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(Pair(false, "")) }

    if (showAlert.first) {
        Alert(
            title = "Error",
            description = showAlert.second,
            onDismissClick = {
                showAlert = showAlert.copy(first = false)
            },
            onConfirmClick = {
                Button(onClick = {
                    showAlert = showAlert.copy(first = false)
                }) {
                    Text(text = "Close")
                }
            }
        )
    }

    val permission = Manifest.permission.CAMERA
    val authority = stringResource(id = R.string.file_provider)
    val takePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                if (state.tempUri != Uri.EMPTY) {
                    state.rotation = 0
                    state.classifierResult = viewModel.classify(
                        MediaStore.Images.Media.getBitmap(context.contentResolver, state.tempUri),
                        state.rotation
                    )
                    showResult = true
                }
            }
        }
    )
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if (it != null) {
                state.tempUri = it
                state.rotation = 0
                state.classifierResult = viewModel.classify(
                    MediaStore.Images.Media.getBitmap(context.contentResolver, state.tempUri),
                    state.rotation
                )
                showResult = true
            }
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
        val result = if (state.classifierResult.isNotEmpty()) {
            state.classifierResult[0].name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
        } else stringResource(
            R.string.unknown
        )
        ScanResultBottomSheet(
            modifier = Modifier,
            imgUri = state.tempUri,
            result = result,
            onCheckNutritionClick = {
                navigateToFoodDetail.invoke(result)
            },
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

    LaunchedEffect(key1 = isAuthenticated) {
        if (isAuthenticated) {
            viewModel.getMe()
            viewModel.getDailyNutritions()
        }
    }

    LaunchedEffect(key1 = state.user) {
        val user = state.user
        if (user != null) {
            if (user.userMetric.age == 0) {
                navigateToEditProfile.invoke(
                    user.fullname,
                    user.email,
                    user.userMetric.age.toString(),
                    user.userMetric.height.toString(),
                    user.userMetric.weight.toString()
                )
                state.user = null
            }
        }
    }

    LaunchedEffect(
        key1 = state.nutritionHistory.size,
        key2 = state.isHistoryDeleted,
        key3 = state.error
    ) {
        if (isAuthenticated) {
            if (state.isHistoryDeleted) {
                viewModel.getDailyNutritions()
                state.isHistoryDeleted = false
            }
        }

        val error = state.error
        if (error.isNotBlank()) {
            showAlert = Pair(true, state.error)
            state.error = ""
        }
    }

    Column(
        modifier = Modifier
            .background(Background)
            .statusBarsPadding()
            .padding(
                bottom = innerPadding.calculateBottomPadding()
            )
    ) {
        if (!isAuthenticated) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        top = 8.dp,
                        end = 24.dp
                    )
                    .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryContainer
                )
            ) {
                UnauthenticatedMessage {
                    navigateToSignIn.invoke()
                }
            }
        } else {
            if (state.nutritionHistory.isNotEmpty()) {
                val minimumNutrition = getMinimumNutrition(state.user!!.userMetric.age)
                DailyNutrition(
                    modifier = Modifier
                        .padding(
                            top = 8.dp
                        ),
                    calorieValue = state.nutritionHistory.sumOf { it.foodNutrition.energy }
                        .toFloat(),
                    calorieMaxValue = minimumNutrition.calorie.toFloat(),
                    proteinValue = state.nutritionHistory.sumOf { it.foodNutrition.protein }
                        .toFloat(),
                    proteinMaxValue = minimumNutrition.protein.toFloat(),
                    carboValue = state.nutritionHistory.sumOf { it.foodNutrition.totalCarbohydrate }
                        .toFloat(),
                    carboMaxValue = minimumNutrition.carbohydrate.toFloat(),
                    fiberValue = state.nutritionHistory.sumOf { it.foodNutrition.dietaryFiber }
                        .toFloat(),
                    fiberMaxValue = minimumNutrition.fiber.toFloat(),
                    fatValue = state.nutritionHistory.sumOf { it.foodNutrition.totalFat }.toFloat(),
                    fatMaxValue = minimumNutrition.fat.toFloat()
                )
            } else {
                DailyNutrition(
                    modifier = Modifier
                        .padding(
                            top = 8.dp
                        ),
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
            }
        }
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
                    navigateToFood.invoke()
                }
            )
            CardButton(
                modifier = Modifier,
                icon = painterResource(id = R.drawable.ic_calculator),
                iconColor = Primary,
                text = stringResource(R.string.bmi),
                onClick = {
                    navigateToBMI.invoke()
                }
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                )
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryContainer
            )
        ) {
            if (state.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(24.dp),
                        color = Primary
                    )
                }
            } else {
                if (!isAuthenticated) {
                    UnauthenticatedMessage {
                        navigateToSignIn.invoke()
                    }
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 16.dp
                            ),
                        text = stringResource(R.string.daily_food_history),
                        color = Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    if (state.nutritionHistory.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = stringResource(R.string.no_history_data),
                                color = Neutral,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 12.dp,
                                    top = 12.dp,
                                    end = 12.dp,
                                    bottom = 16.dp
                                ),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(state.nutritionHistory) { history ->
                                FoodItem(
                                    modifier = Modifier,
                                    dailyNutritionId = history.dailyNutritionId,
                                    imgUrl = history.foodImage,
                                    foodName = history.foodName,
                                    calorie = history.foodNutrition.energy.toInt(),
                                    isHistory = true,
                                    onClick = {
                                        navigateToFoodDetail.invoke(history.foodName)
                                    },
                                    onIconClick = {
                                        viewModel.deleteDailyNutrition(history.dailyNutritionId)
                                    }
                                )
                            }
                        }
                    }
                }
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