package com.dplong.sample_project.ui.main.first

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class FirstViewModel : ViewModel() {
    sealed class Event {
        object NavToSecondScreen : Event()
    }

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()
    var email by mutableStateOf("")
        private set

    fun sentEmail(value: String) {
        email = value
    }

    fun onSecondClick() {
        viewModelScope.launch {
            _eventChannel.send(Event.NavToSecondScreen)
        }
    }
}