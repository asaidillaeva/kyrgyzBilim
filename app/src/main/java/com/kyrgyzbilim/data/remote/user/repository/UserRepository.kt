package com.kyrgyzbilim.data.remote.user

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.topic.Topic
import retrofit2.Response

interface UserRepository {
    suspend fun register(r: RegisterRequestBody): ApiResult<Response<RegisterResponse>>
    suspend fun login(l: LoginRequestBody): ApiResult<Response<LoginResponse>>
}