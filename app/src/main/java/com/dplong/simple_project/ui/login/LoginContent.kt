package com.dplong.simple_project.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dplong.simple_project.R
import com.dplong.simple_project.ui.compose.AppText
import com.dplong.simple_project.ui.compose.AppTextField
import com.dplong.simple_project.ui.compose.PasswordTextField

@Composable
fun LoginContent(vm: LoginViewModel) {
    LoginContentView(
        state = vm.state,
        onEmailChange = vm::onEmailChange,
        onPasswordChange = vm::onPasswordChange,
        onLoginClick = vm::onLoginClick
    )
}

@Composable
internal fun LoginContentView(
    state: LoginState = LoginState.Empty,
    onLoginClick: () -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppText(
            text = stringResource(R.string.email),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.email,
            onValueChange = onEmailChange,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                )
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        AppText(
            text = stringResource(R.string.password),
            fontSize = 16.sp,
        )
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.password,
            onValueChange = onPasswordChange,
            placeholder = "",
            hasError = false,
            maxLength = 16,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Filled.Password,
                    contentDescription = null,
                )
            },
        )
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .clickable(
                    onClick = onLoginClick,
                )
                .clip(CircleShape)
                .border(width = 2.dp, color = Color.Cyan, shape = CircleShape)
                .background(color = Color.Black.copy(alpha = 0.75f))
        )
        {
            AppText(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                text = stringResource(R.string.login),
                style = TextStyle(color = Color.White),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }

}

@Preview(backgroundColor = 0xFF000000)
@Composable
fun ComposablePreview() {
    LoginContentView()
}