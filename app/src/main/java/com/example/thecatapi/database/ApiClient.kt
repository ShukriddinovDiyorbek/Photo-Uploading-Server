package com.example.thecatapi.database

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(context: Context) {
    val chuckInterceptor = ChuckerInterceptor(context)

    val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(chuckInterceptor)
        .build()
    val retrofit = Retrofit
        .Builder()
        .baseUrl(server())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        private val IS_TESTER = true
        private val SERVER_DEVELOPMENT = "https://api.thecatapi.com/v1/"
        private val SERVER_PRODUCTION = "https://api.thecatapi.com/v1/"
        fun server(): String {
            if (IS_TESTER) return SERVER_DEVELOPMENT
            return SERVER_PRODUCTION
        }
    }

    val apiServise: ApiServise =retrofit.create(ApiServise::class.java)
}