package com.kyrgyzbilim.data.api.models

data class LoginRequestBody(
    var phone_number: String,
    var password: String
) {

}