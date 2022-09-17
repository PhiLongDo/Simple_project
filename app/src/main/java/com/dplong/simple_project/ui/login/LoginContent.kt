package com.dplong.simple_project.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = stringResource(R.string.email))
        TextField(value = state.email, onValueChange = onEmailChange)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(R.string.password))
        TextField(value = state.password, onValueChange = onPasswordChange)
        Box(modifier = Modifier.clickable { onLoginClick() })
        {
            Text(text = stringResource(R.string.login))
        }
    }

}

@Preview
@Composable
fun ComposablePreview() {
    LoginContentView()
}