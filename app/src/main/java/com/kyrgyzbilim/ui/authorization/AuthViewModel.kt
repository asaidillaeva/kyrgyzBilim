package com.kyrgyzbilim.ui.authorization

import androidx.lifecycle.*
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.user.LoginRequestBody
import com.kyrgyzbilim.data.remote.user.RegisterRequestBody
import com.kyrgyzbilim.data.remote.user.repository.UserRepository

class AuthViewModel(val userRepository: UserRepository) : ViewModel() {
    private lateinit var state: SavedStateHandle
    private lateinit var nameContainer: MutableLiveData<String>
    init {
        fun AuthViewModel(state: SavedStateHandle) {
            this.state = state
            nameContainer = state.getLiveData("Default Message")
        }
    }
    var userLoginData: LoginRequestBody? = null
    var userRegisterData: RegisterRequestBody? = null

    fun login() = liveData {
        emit(ApiResult.Loading)
        userLoginData?.let {
            val result = userRepository.login(it)
            emit(result)
        }
    }


    fun register() = liveData {
        emit(ApiResult.Loading)
        userRegisterData?.let {
            val result = userRepository.register(it)
            emit(result)
        }
    }
}