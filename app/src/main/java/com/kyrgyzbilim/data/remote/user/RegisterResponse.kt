package com.kyrgyzbilim.data.remote.user

data class RegisterResponse(
    val error: String,
    val invalid_args: ArrayList<InvalidArgs>,
    val message: String
) {

}
