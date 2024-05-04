package com.uiel.scul.network.api

import com.uiel.scul.model.user.LoginRequest
import com.uiel.scul.model.user.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApi {

    @POST("scul/users/signup")
    suspend fun signup(

    )

    @POST("scul/users/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse
}
