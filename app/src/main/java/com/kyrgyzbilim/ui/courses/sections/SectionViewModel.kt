package com.kyrgyzbilim.ui.courses.sections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.course.repository.CourseRepository
import com.kyrgyzbilim.data.sections.SectionRepository

class SectionViewModel(
    private val sectionRepository: SectionRepository
) : ViewModel() {


    val section = liveData {
        emit(ApiResult.Loading)
        val result = sectionRepository.getSectionsList(5)
        emit(result)
    }



}