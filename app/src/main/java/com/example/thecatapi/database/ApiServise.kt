package com.example.thecatapi.database

import com.example.thecatapi.model.ResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiServise {
    @Headers(
        "x-api-key: 056ccbae-10d1-42f8-9593-288be4b8687d"
    )
    @GET("images/search")
    fun getAll(
        @Query("limit") per_page: Int = 20,
        @Query("page") page: Int
    ):Call<ArrayList<ResponseItem>>

    @GET("images")
    fun getMyCats(
        @Query("page") page: Int,
        @Query("limit") per_page: Int = 20)
            :Call<ArrayList<ResponseItem>>
}