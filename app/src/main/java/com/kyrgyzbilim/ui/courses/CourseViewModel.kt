package com.kyrgyzbilim.ui.courses

import androidx.lifecycle.*
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.course.repository.CourseRepository
import com.kyrgyzbilim.data.remote.user.repository.UserRepository

class CourseViewModel (
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private lateinit var state: SavedStateHandle
    private lateinit var nameContainer: MutableLiveData<String>

    init {
        fun CourseViewModel(state: SavedStateHandle) {
            this.state = state
            nameContainer = state.getLiveData("Default Message")
        }
    }

    fun course(token: String) = liveData {
        emit(ApiResult.Loading)
        val result = courseRepository.getCourseList(token)
        emit(result)
    }

    fun user(token: String) = liveData {
        emit(ApiResult.Loading)
        val result = userRepository.info(token)
        emit(result)
    }
}
