package com.farchase.indicfriendchat.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farchase.indicfriendchat.data.ChatRepository
import com.farchase.indicfriendchat.data.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repo = ChatRepository()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    fun send(text: String) {
        _messages.value = _messages.value + ChatMessage("user", text)

        viewModelScope.launch {
            try {
                val reply = repo.sendMessage(text)
                _messages.value = _messages.value + ChatMessage("assistant", reply)
            } catch (e: Exception) {
                _messages.value = _messages.value + ChatMessage("assistant", "Server error 😅")
            }
        }
    }
}
