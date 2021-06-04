package com.kyrgyzbilim.data.remote.user.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.apiCall
import com.kyrgyzbilim.data.remote.ServiceClient
import com.kyrgyzbilim.data.remote.user.LoginRequestBody
import com.kyrgyzbilim.data.remote.user.LoginResponse
import com.kyrgyzbilim.data.remote.user.RegisterRequestBody
import com.kyrgyzbilim.data.remote.user.RegisterResponse
import retrofit2.Response

class UserRepositoryImpl(
    private val serviceClient: ServiceClient
): UserRepository {
    override suspend fun register(r: RegisterRequestBody): ApiResult<Response<RegisterResponse>> {
        return apiCall { serviceClient.register(r) }

    }
    override suspend  fun login(l: LoginRequestBody): ApiResult<Response<LoginResponse>> {
        return apiCall { serviceClient.login(l) }
    }
}