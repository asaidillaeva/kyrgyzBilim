package com.kyrgyzbilim.ui.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyrgyzbilim.data.remote.course.repository.CourseRepository
import com.kyrgyzbilim.data.remote.user.repository.UserRepository

class CourseViewModelFactory (
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CourseViewModel::class.java) ->
                CourseViewModel(courseRepository, userRepository) as T
            else -> throw IllegalArgumentException("Not found")
        }
    }
}