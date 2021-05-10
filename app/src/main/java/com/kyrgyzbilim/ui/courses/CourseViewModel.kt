package com.kyrgyzbilim.ui.courses

import android.util.Log
import androidx.lifecycle.*
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.course.repository.CourseRepository

class CourseViewModel (
    private val courseRepository: CourseRepository
) : ViewModel() {

    private lateinit var state: SavedStateHandle
    private lateinit var nameContainer: MutableLiveData<String>

    init {
        fun CourseViewModel(state: SavedStateHandle) {
            this.state = state
            nameContainer = state.getLiveData("Default Message")
        }
    }



    val course = liveData {
        emit(ApiResult.Loading)
        val result = courseRepository.getCourseList()
        emit(result)
    }



}