package com.kyrgyzbilim.data.remote.user.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.user.LoginRequestBody
import com.kyrgyzbilim.data.remote.user.LoginResponse
import com.kyrgyzbilim.data.remote.user.RegisterRequestBody
import com.kyrgyzbilim.data.remote.user.RegisterResponse
import retrofit2.Response

interface UserRepository {
    suspend fun register(r: RegisterRequestBody): ApiResult<RegisterResponse>
    suspend fun login(l: LoginRequestBody): ApiResult<LoginResponse>
}