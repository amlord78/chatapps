package com.farchase.indicfriendchat.data.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    // 🔴 CHANGE THIS TO YOUR VPS
    private const val BASE_URL = "http://YOUR_VPS_IP:8000/"

    val api: ChatApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApi::class.java)
    }
}
