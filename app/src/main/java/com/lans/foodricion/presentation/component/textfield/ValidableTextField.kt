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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.lans.foodricion.domain.model.InputWrapper
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.RoundedMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidableTextField(
    modifier: Modifier,
    shape: Shape = RoundedMedium,
    input: InputWrapper,
    isEnable: Boolean = true,
    isReadOnly: Boolean = false,
    isPassword: Boolean = false,
    isSupportiveText: Boolean = true,
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
    val supportiveText: @Composable (() -> Unit)? = if (isSupportiveText) {
        {
            if (input.error != null) Text(input.error!!)
        }
    } else {
        null
    }

    BasicTextField(
        modifier = modifier,
        value = input.value,
        textStyle = textStyle,
        readOnly = isReadOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = if (passwordVisible && isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = input.value,
                innerTextField = innerTextField,
                enabled = isEnable,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                isError = input.error != null,
                label = { if (label != null) Text(label) },
                placeholder = { if (placeholder != null) Text(placeholder) },
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
                supportingText = supportiveText,
                colors = OutlinedTextFieldDefaults.colors(),
                contentPadding = OutlinedTextFieldDefaults.contentPadding(),
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = isEnable,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = Primary
                        ),
                        shape = shape,
                        focusedBorderThickness = 2.dp,
                        unfocusedBorderThickness = 2.dp,
                    )
                },
            )
        },
        onValueChange = onValueChange
    )
}