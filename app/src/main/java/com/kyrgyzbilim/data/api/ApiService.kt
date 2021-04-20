package com.kyrgyzbilim.data.api

import com.kyrgyzbilim.data.api.models.LoginRequestBody
import com.kyrgyzbilim.data.api.models.LoginResponse
import com.kyrgyzbilim.data.api.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("v1/auth/register")
    suspend fun listCategories(): Response<RegisterResponse>

    @POST("/v1/auth/login")
    suspend fun  login( @Body adv: LoginRequestBody): Response<LoginResponse>
}