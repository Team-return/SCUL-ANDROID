package com.uiel.scul.model.user

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("name") val name: String,
    @SerializedName("accountId") val accountId: String,
    @SerializedName("password") val password: String,
)
