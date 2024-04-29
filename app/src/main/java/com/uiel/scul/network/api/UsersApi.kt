package com.uiel.scul.network.api

import com.uiel.scul.model.user.LoginRequest
import retrofit2.http.POST

interface UsersApi {

    @POST("/signup")
    suspend fun signup(

    )

    @POST("/login")
    suspend fun login(
        loginRequest: LoginRequest,
    )
}