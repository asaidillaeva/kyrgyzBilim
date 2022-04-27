package com.kyrgyzbilim.ui.courses.sections.subtopics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.subTopic.TrackProgressResponse
import com.kyrgyzbilim.data.remote.subTopic.repository.SubTopicRepository
import com.kyrgyzbilim.data.remote.user.LoginRequestBody

class SubTopicViewModel(
    private val subTopicRepository: SubTopicRepository
) : ViewModel() {

    private var idTopic: Int = 0
    private var token: String? = null
    var courseId: Int? = null


    fun setTopic(idSet: Int) {
        idTopic = idSet
    }


    fun setToken(tokenSet: String) {
        token = tokenSet
    }

    val subTopic = liveData {
        emit(ApiResult.Loading)
        val result = subTopicRepository.getSubTopic(token, idTopic)
        emit(result)
    }

    fun trackProgress() = liveData {
        emit(ApiResult.Loading)
        courseId?.let {
            val result = subTopicRepository.trackProgress(it)
            emit(result)
        }
    }
}
