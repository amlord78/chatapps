package com.farchase.indicfriendchat.data.model

data class ChatMessage(
    val id: Long = 0,
    val role: String,     // "user" / "assistant"
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
