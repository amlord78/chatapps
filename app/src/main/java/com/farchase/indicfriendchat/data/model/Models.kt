package com.farchase.indicfriendchat.data.model

data class ChatRequest(
    val message: String,
    val profile: Profile = Profile()
)

data class ChatResponse(
    val reply: String
)

data class Profile(
    val botName: String = "Dost",
    val state: String = "India",
    val language: String = "auto"
)

data class ChatMessage(
    val role: String, // "user" or "assistant"
    val text: String
)
