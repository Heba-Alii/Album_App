package com.example.albumapp.domain.model.album


import com.google.gson.annotations.SerializedName

data class AlbumResponseItem(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("userId")
    val userId: Int? = null
)