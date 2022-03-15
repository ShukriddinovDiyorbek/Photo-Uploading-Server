package com.example.thecatapi.model

import com.google.gson.annotations.SerializedName

data class ResponseItem(

	@field:SerializedName("approved")
	val approved: Int? = null,

	@field:SerializedName("original_filename")
	val originalFilename: String? = null,

	@field:SerializedName("sub_id")
	val subId: String? = null,

	@field:SerializedName("pending")
	val pending: Int? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
