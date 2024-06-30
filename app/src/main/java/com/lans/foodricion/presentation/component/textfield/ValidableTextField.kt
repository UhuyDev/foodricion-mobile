package com.lans.foodricion.presentation.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidableTextField(
    modifier: Modifier,
    shape: Shape = RoundedMedium,
    input: String,
    isEnable: Boolean = true,
    isReadOnly: Boolean = false,
    isPassword: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String? = null,
    placeholder: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = 4,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    keyboardActions: KeyboardActions = KeyboardActions(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (value: String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(true) }

    BasicTextField(
        modifier = modifier,
        value = input,
        textStyle = textStyle,
        readOnly = isReadOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = if (passwordVisible && isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = input,
                innerTextField = innerTextField,
                enabled = isEnable,
                label = { if (label != null) Text(label) },
                placeholder = { if (placeholder != null) Text(placeholder) },
                singleLine = singleLine,
                leadingIcon = leadingIcon,
                trailingIcon = if (trailingIcon == null && isPassword) {
                    {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    }
                } else trailingIcon,
                interactionSource = interactionSource,
                visualTransformation = visualTransformation,
                container = {
                    TextFieldDefaults.OutlinedBorderContainerBox(
                        enabled = isEnable,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = Primary
                        ),
                        shape = shape,
                        focusedBorderThickness = 2.dp,
                        unfocusedBorderThickness = 2.dp
                    )
                }
            )
        },
        onValueChange = onValueChange
    )
}