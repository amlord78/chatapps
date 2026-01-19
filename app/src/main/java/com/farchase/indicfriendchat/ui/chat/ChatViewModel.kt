package com.farchase.indicfriendchat.ui.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.farchase.indicfriendchat.data.model.ChatMessage
import com.farchase.indicfriendchat.data.repo.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = ChatRepository(app.applicationContext)

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    init {
        // Load saved messages (Room) and show in UI
        viewModelScope.launch {
            repo.observeMessages().collect { _messages.value = it }
        }
    }

    fun send(text: String) {
        viewModelScope.launch {
            repo.sendMessage(text)
        }
    }

    fun clearChat() {
        viewModelScope.launch { repo.clear() }
    }
}
