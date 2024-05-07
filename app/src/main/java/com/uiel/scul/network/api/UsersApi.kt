package com.uiel.scul.network.api

import com.uiel.scul.model.user.FetchNameResponse
import com.uiel.scul.model.user.LoginRequest
import com.uiel.scul.model.user.LoginResponse
import com.uiel.scul.model.user.SignupRequest
import com.uiel.scul.model.user.SignupResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UsersApi {

    @POST("scul/users/signup")
    suspend fun signup(
        @Body signupRequest: SignupRequest,
    ): SignupResponse

    @POST("scul/users/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse

    @GET("scul/users/name")
    suspend fun fetchName(
        @Header("Authorization") authorization: String,
    ): FetchNameResponse
}
