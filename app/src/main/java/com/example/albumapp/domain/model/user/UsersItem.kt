package com.example.albumapp.domain.model.user


import com.google.gson.annotations.SerializedName

data class UsersItem(
    @SerializedName("address")
    val address: Address? = null,
    @SerializedName("company")
    val company: Company? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("website")
    val website: String? = null
)