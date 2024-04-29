package com.uiel.scul.model.user

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("accountId") val accountId: String,
    @SerializedName("password") val password: String,
)
