package com.dplong.sample_project.ui.main.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SecondViewModel : ViewModel() {
    sealed class Event {
        object NavToFirstScreen : Event()
        object Back : Event()
    }

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()
    fun onFirstClick() {
        viewModelScope.launch {
            _eventChannel.send(Event.NavToFirstScreen)
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _eventChannel.send(Event.Back)
        }
    }
}