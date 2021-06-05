package com.kyrgyzbilim.data.remote.user.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.apiCall
import com.kyrgyzbilim.data.remote.ServiceClient
import com.kyrgyzbilim.data.remote.user.*

class UserRepositoryImpl(
    private val serviceClient: ServiceClient
): UserRepository {
    override suspend fun register(r: RegisterRequestBody): ApiResult<RegisterResponse> {
        return apiCall {
            serviceClient.register(
                "",
                r.first_name,
                r.last_name,
                r.phone_number,
                r.password)
        }
    }
    override suspend  fun login(l: LoginRequestBody): ApiResult<LoginResponse> {
        return apiCall { serviceClient.login(l) }
    }

    override suspend fun info(token: String): ApiResult<User> {
        return apiCall { serviceClient.getUser("Bearer $token") }
    }
}