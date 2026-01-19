package com.farchase.indicfriendchat.data.repo

import android.content.Context
import com.farchase.indicfriendchat.data.db.AppDatabase
import com.farchase.indicfriendchat.data.db.ChatEntity
import com.farchase.indicfriendchat.data.model.ChatMessage
import com.farchase.indicfriendchat.data.net.Network
import com.farchase.indicfriendchat.data.net.dto.ChatRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepository(context: Context) {
    private val dao = AppDatabase.get(context).chatDao()
    private val api = Network.createApi()

    fun observeMessages(): Flow<List<ChatMessage>> =
        dao.observeAll().map { list ->
            list.map { ChatMessage(it.id, it.role, it.content, it.timestamp) }
        }

    suspend fun sendMessage(userText: String) {
        // save user message
        dao.insert(ChatEntity(role = "user", content = userText, timestamp = System.currentTimeMillis()))

        // call backend
        val resp = api.chat(ChatRequest(message = userText))

        // save assistant message
        dao.insert(ChatEntity(role = "assistant", content = resp.reply, timestamp = System.currentTimeMillis()))
    }

    suspend fun clear() = dao.clear()
}
