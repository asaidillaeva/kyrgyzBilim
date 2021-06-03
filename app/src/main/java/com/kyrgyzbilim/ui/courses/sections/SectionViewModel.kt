package com.kyrgyzbilim.ui.courses.sections

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.sections.repository.SectionRepository

class SectionViewModel(
    private val sectionRepository: SectionRepository
) : ViewModel() {
    private var idCourse: Int = 0
    private var idSection: Int = 0

    fun setCourseId(idSet: Int){
        idCourse = idSet
    }

    fun setSectionId(idSet: Int){
        idSection = idSet
    }


    val section = liveData {
        emit(ApiResult.Loading)
        val result = sectionRepository.getSectionsList(idCourse)
        emit(result)
    }

    val topics = liveData {
        emit(ApiResult.Loading)
        Log.e("section id in ve model", idSection.toString())
        val result = sectionRepository.getTopic(idSection)
        emit(result)
    }



}