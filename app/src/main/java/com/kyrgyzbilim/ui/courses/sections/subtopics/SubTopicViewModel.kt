package com.kyrgyzbilim.ui.courses.sections.subtopics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.subTopic.repository.SubTopicRepository

class SubTopicViewModel(
    private val subTopicRepository: SubTopicRepository
) : ViewModel() {

    private var idTopic: Int = 0

    fun setTopic(idSet: Int) {
        idTopic = idSet
    }

    private var token: String? = null

    fun setToken(tokenSet: String) {
        token = tokenSet
    }


    val subTopic = liveData {
        emit(ApiResult.Loading)
        val result = subTopicRepository.getSubTopic(token, idTopic)
        emit(result)
    }
}
