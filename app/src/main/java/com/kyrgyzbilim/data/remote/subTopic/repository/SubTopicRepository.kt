package com.kyrgyzbilim.data.remote.subTopic.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import com.kyrgyzbilim.data.remote.subTopic.TrackProgressResponse

interface SubTopicRepository {
    suspend fun getSubTopic(token: String?, id: Int): ApiResult<List<SubTopic>>
    suspend fun trackProgress(courseId: Int): ApiResult<TrackProgressResponse>

}
