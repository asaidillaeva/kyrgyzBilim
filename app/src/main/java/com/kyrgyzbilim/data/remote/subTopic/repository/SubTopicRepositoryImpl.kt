package com.kyrgyzbilim.data.remote.subTopic.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.apiCall
import com.kyrgyzbilim.data.remote.ServiceClient
import com.kyrgyzbilim.data.remote.subTopic.CourseIdObj
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import com.kyrgyzbilim.data.remote.subTopic.TrackProgressResponse

class SubTopicRepositoryImpl(
    private val serviceClient: ServiceClient
) : SubTopicRepository {
    override suspend fun getSubTopic(token: String?, id: Int): ApiResult<List<SubTopic>> {
        return apiCall { serviceClient.getSubTopics(("Bearer $token"), id) }

    }

    override suspend fun trackProgress(
        token: String,
        courseId: CourseIdObj,
        subtopicId: Int,
    ): ApiResult<TrackProgressResponse> {
        return apiCall { serviceClient.trackProgress("Bearer $token", courseId, subtopicId) }
    }
}
