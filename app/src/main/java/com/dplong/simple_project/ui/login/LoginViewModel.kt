package com.dplong.simple_project.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    sealed class LoginEvent {
        object Login : LoginEvent()
    }

    private val _eventChannel = Channel<LoginEvent>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()

    var state by mutableStateOf(LoginState.Empty)
        private set

    fun onEmailChange(value: String) {
        state = state.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value)
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _eventChannel.send(
                LoginEvent.Login
            )
        }
    }
}