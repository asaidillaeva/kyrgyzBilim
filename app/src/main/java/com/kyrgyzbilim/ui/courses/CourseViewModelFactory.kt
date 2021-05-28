package com.kyrgyzbilim.ui.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyrgyzbilim.data.remote.course.repository.CourseRepository

class CourseViewModelFactory (
    private val courseRepository: CourseRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CourseViewModel::class.java) ->
                CourseViewModel(courseRepository) as T
            else -> throw IllegalArgumentException("Not found")
        }
    }
}