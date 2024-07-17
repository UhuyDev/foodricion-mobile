package com.lans.foodricion.presentation.screen.chatbot

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.lans.foodricion.R
import com.lans.foodricion.domain.model.InputWrapper
import com.lans.foodricion.presentation.component.alert.Alert
import com.lans.foodricion.presentation.component.message_bubble.MessageBubble
import com.lans.foodricion.presentation.component.textfield.ValidableTextField
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.White

@Composable
fun ChatBotScreen(
    viewModel: ChatbotViewModel = hiltViewModel(),
    isAuthenticated: Boolean,
    navigateToSignIn: () -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.state
    val chatState = rememberLazyListState()
    var showAlert by remember { mutableStateOf(Pair(false, "")) }
    var showUnauthenticated by remember { mutableStateOf(false) }

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

    if (showUnauthenticated) {
        Alert(
            title = "Your session is expired",
            description = "Please sign in again",
            onDismissClick = {
                showUnauthenticated = false
            },
            onConfirmClick = {
                Button(onClick = {
                    showUnauthenticated = false
                    navigateToSignIn.invoke()
                }) {
                    Text(text = "Sign In")
                }
            }
        )
    }

    LaunchedEffect(key1 = isAuthenticated) {
        showUnauthenticated = !isAuthenticated
    }

    LaunchedEffect(key1 = state.messages.size, key2 = state.isMessageSent, key3 = state.error) {
        if(!showUnauthenticated) {
            val error = state.error

            if(state.isMessageSent) {
                viewModel.getMessages()
            }
            chatState.animateScrollToItem(chatState.layoutInfo.totalItemsCount)

            if (error.isNotBlank()) {
                showAlert = Pair(true, state.error)
                state.error = ""
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        val (topBar, messages, chatBox) = createRefs()

        Row(
            modifier = Modifier
                .background(Primary)
                .fillMaxWidth()
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    bottom.linkTo(messages.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navigateBack.invoke()
                    },
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
                .fillMaxWidth()
                .constrainAs(messages) {
                    top.linkTo(topBar.bottom)
                    bottom.linkTo(chatBox.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
                .padding(
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = chatState
        ) {
            items(state.messages) { message ->
                MessageBubble(
                    modifier = Modifier,
                    message = message.message,
                    isUser = true
                )
                MessageBubble(
                    modifier = Modifier,
                    message = message.response,
                    isUser = false
                )
            }
        }
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding()
                .constrainAs(chatBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                }
                .padding(16.dp)
        ) {
            ValidableTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                input = state.message,
                isSupportiveText = false,
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(ChatbotUIEvent.SendButtonClicked)
                        state.message = InputWrapper()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_send),
                            tint = Primary,
                            contentDescription = stringResource(id = R.string.content_description)
                        )
                    }
                },
                onValueChange = {
                    viewModel.onEvent(ChatbotUIEvent.MessageChanged(it))
                }
            )
        }
    }
}
