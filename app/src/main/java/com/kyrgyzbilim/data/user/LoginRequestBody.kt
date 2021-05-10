package com.kyrgyzbilim.data.user

data class LoginRequestBody(
    var phone_number: String,
    var password: String
) {

}