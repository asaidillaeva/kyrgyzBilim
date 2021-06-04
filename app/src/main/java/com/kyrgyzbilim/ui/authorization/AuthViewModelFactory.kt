package com.kyrgyzbilim.ui.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyrgyzbilim.data.remote.user.repository.UserRepository

class AuthViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(userRepository) as T
            else -> throw IllegalArgumentException("Not found")
        }
    }
}