package com.dplong.simple_project.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    hasError: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    maxLength: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color(0xFF565B67)),
    borderThickness: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(
        start = 16.dp,
        end = 16.dp,
        top = 8.dp,
        bottom = 8.dp
    ),
    normalBackgroundColor: Color = Color(0xFFF1F1F1),
    normalBorderColor: Color = Color(0xFF828EF8),
) {
    val shape = RoundedCornerShape(4.dp)

    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }

    val backgroundColor = if (hasError) {
        Color(0xFFFF0000)
    } else if (enabled) {
        normalBackgroundColor
    } else {
        Color(0xFFB5B5B5)
    }

    val overriddenColors = TextFieldDefaults.outlinedTextFieldColors(
        backgroundColor = backgroundColor,
        focusedBorderColor = if (borderThickness == 0.dp) Color.Transparent else normalBorderColor,
        disabledBorderColor = if (borderThickness == 0.dp) Color.Transparent else normalBorderColor,
        unfocusedBorderColor = if (borderThickness == 0.dp) Color.Transparent else normalBorderColor,
        errorBorderColor = if (borderThickness == 0.dp) Color.Transparent else normalBorderColor
    )

    val mergedTextStyle = textStyle.merge(
        TextStyle(
            color = textColor,
            fontSize = 15.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )
    )

    BasicTextField(
        value = value,
        modifier = if (label != null) {
            modifier.padding(top = 0.dp)
        } else {
            modifier
        }
            .background(overriddenColors.backgroundColor(enabled).value, shape)
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = 48.dp
            ),
        onValueChange = { newValue ->
            var maxLinesValid = true
            if (maxLines != Int.MAX_VALUE) {
                val numberOfNewLine = newValue.count { it == '\n' }
                if (numberOfNewLine >= maxLines) {
                    maxLinesValid = false
                }
            }
            if (maxLinesValid && newValue.length <= maxLength) {
                onValueChange(newValue)
            }
        },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(overriddenColors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            if (leadingIcon == null || singleLine) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = value,
                    visualTransformation = visualTransformation,
                    innerTextField = innerTextField,
                    placeholder = placeholder,
                    label = label,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    singleLine = singleLine,
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = overriddenColors,
                    border = {
                        TextFieldDefaults.BorderBox(
                            enabled = enabled,
                            isError = isError,
                            interactionSource = interactionSource,
                            colors = overriddenColors,
                            shape = shape,
                            focusedBorderThickness = borderThickness,
                            unfocusedBorderThickness = borderThickness,
                        )
                    },
                    contentPadding = contentPadding
                )
            } else {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(color = backgroundColor, shape = shape)
                        .padding(top = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(36.dp)
                            .height(38.dp)
                            .padding(start = 12.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        leadingIcon()
                    }
                    TextFieldDefaults.OutlinedTextFieldDecorationBox(
                        value = value,
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        label = label,
                        leadingIcon = null,
                        trailingIcon = trailingIcon,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = overriddenColors,
                        border = {
                            TextFieldDefaults.BorderBox(
                                enabled = enabled,
                                isError = isError,
                                interactionSource = interactionSource,
                                colors = overriddenColors,
                                shape = shape,
                                focusedBorderThickness = borderThickness,
                                unfocusedBorderThickness = borderThickness,
                            )
                        },
                        contentPadding = contentPadding
                    )
                }
            }
        }
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: String,
    hasError: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    iconVisibilityAlpha: Float = 1f,
    textColor: Color = Color(0xFF575B66),
) {
    val visibility = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    AppTextField(
        hasError = hasError,
        modifier = modifier,
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = textColor),
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        maxLength = maxLength,
        trailingIcon = {
            Icon(
                imageVector = if (!visibility.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .alpha(iconVisibilityAlpha)
                    .clickable {
                        visibility.value = !visibility.value
                    },
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
        },
        placeholder = {
            AppText(
                text = placeholder,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFFC3CAD9),
                fontSize = 15.sp,
            )
        },
        visualTransformation = if (visibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AppTextFieldPreview() {
    var numCycles by remember {
        mutableStateOf("hello")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = numCycles,
            onValueChange = {
                numCycles = it
            },
            singleLine = true,
            enabled = false
        )
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = numCycles,
            onValueChange = {
                numCycles = it
            },
            singleLine = true,
            enabled = false,
            hasError = true,
        )
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = numCycles,
            onValueChange = {
                numCycles = it
            },
            singleLine = true,
            enabled = false,
            normalBackgroundColor = Color.Transparent,
            borderThickness = 1.dp,
            normalBorderColor = Color(0xFFC3CAD9),
        )
        Spacer(modifier = Modifier.height(4.dp))
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = numCycles,
            onValueChange = {
                numCycles = it
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                    tint = Color.Yellow
                )
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        PasswordTextField(
            value = "12345678",
            onValueChange = {},
            placeholder = "******",
            hasError = false,
            maxLength = 16,
        )
    }
}
