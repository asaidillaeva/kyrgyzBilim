package com.kyrgyzbilim.data.remote.user

data class LoginResponse(
    val error: String?,
    val invalid_args: ArrayList<InvalidArgs>,
    val refresh_token: String?,
    val access_token: String?
) {

}

