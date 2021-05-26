package com.kyrgyzbilim.data.remote.user

data class LoginRequestBody(
    var phone_number: String,
    var password: String
) {

}