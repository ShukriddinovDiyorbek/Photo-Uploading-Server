package com.example.thecatapi.database

import com.example.thecatapi.model.ResponseItem
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServise {
    @Headers(
        "x-api-key: 056ccbae-10d1-42f8-9593-288be4b8687d"
    )
    @GET("images/search")
    fun getAll(
        @Query("limit") per_page: Int = 20,
        @Query("page") page: Int
    ):Call<ArrayList<ResponseItem>>

    @Headers(
        "x-api-key: 056ccbae-10d1-42f8-9593-288be4b8687d"
    )

    @Multipart
    @POST("images/upload")
    fun uploadFile(@Part image: MultipartBody.Part, @Part("sub_id") name: String): Call<ResponseItem>

    @GET("images")
    fun getMyCats(
        @Query("page") page: Int,
        @Query("limit") per_page: Int = 20)
            :Call<ArrayList<ResponseItem>>
}