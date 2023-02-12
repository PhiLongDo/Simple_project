package com.dplong.simple_project.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
        Text(
            text = stringResource(R.string.email),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = state.email, onValueChange = onEmailChange)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.password),
            fontSize = 16.sp,
        )
        TextField(value = state.password, onValueChange = onPasswordChange)
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
            Text(
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