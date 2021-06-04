package com.kyrgyzbilim.data.remote.user

data class RegisterRequestBody(
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val password: String
){

}
