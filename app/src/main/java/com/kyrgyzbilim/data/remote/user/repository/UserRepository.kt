package com.kyrgyzbilim.data.remote.user.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.user.*
import retrofit2.Response

interface UserRepository {
    suspend fun register(r: RegisterRequestBody): ApiResult<RegisterResponse>
    suspend fun login(l: LoginRequestBody): ApiResult<LoginResponse>
    suspend fun info(token: String): ApiResult<User>
}
