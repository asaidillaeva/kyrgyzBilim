package com.kyrgyzbilim.ui.courses.sections.subtopics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.subTopic.SubTopicRepository

class SubTopicViewModel(
    private val subTopicRepository: SubTopicRepository
) : ViewModel() {

    private var idTopic: Int = 0

    fun setTopic(idSet: Int){
        idTopic = idSet
    }


    val subTopic = liveData {
        emit(ApiResult.Loading)
        val result = subTopicRepository.getSubTopic(idTopic)
        emit(result)
    }
}