package com.example.nye_175_173_login_signup.network

import com.example.nye_175_173_login_signup.model.AuthRequest
import com.example.nye_175_173_login_signup.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("X-ACCESS-KEY: 1729")
    @POST("auth/signup")
    suspend fun signup(@Body request: AuthRequest): Response<String>

    @Headers("X-ACCESS-KEY: 1729")
    @POST("auth/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>
}
