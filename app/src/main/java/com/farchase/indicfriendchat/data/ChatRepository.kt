package com.farchase.indicfriendchat.data

import com.farchase.indicfriendchat.data.model.ChatRequest
import com.farchase.indicfriendchat.data.model.ChatMessage
import com.farchase.indicfriendchat.data.net.Network

class ChatRepository {

    private val api = Network.api

    suspend fun sendMessage(msg: String): String {
        val res = api.chat(ChatRequest(message = msg))
        return res.reply
    }
}
