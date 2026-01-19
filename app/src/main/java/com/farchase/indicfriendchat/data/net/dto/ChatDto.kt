package com.farchase.indicfriendchat.data.net.dto

data class ChatRequest(
    val message: String
)

data class ChatResponse(
    val reply: String
)
