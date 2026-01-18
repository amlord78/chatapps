package com.farchase.indicfriendchat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen() {
    var text by remember { mutableStateOf("") }
    var reply by remember { mutableStateOf("Haan bolo 🙂") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(reply, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.weight(1f))
        Row {
            OutlinedTextField(text, { text = it }, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (text.isNotBlank()) {
                    reply = "Message sent: $text"
                    text = ""
                }
            }) { Text("Send") }
        }
    }
}
