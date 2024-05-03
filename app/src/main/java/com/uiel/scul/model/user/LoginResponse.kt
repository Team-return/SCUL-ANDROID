package com.uiel.scul.model.user

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("accessTokenExp") val accessTokenExp: String,
    @SerializedName("refreshToken") val refreshToken: String,
)
