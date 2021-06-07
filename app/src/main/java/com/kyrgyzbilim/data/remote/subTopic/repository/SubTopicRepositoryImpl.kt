package com.kyrgyzbilim.data.remote.subTopic.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.apiCall
import com.kyrgyzbilim.data.remote.ServiceClient
import com.kyrgyzbilim.data.remote.subTopic.SubTopic

class SubTopicRepositoryImpl(
    private val serviceClient: ServiceClient
): SubTopicRepository {
    override suspend fun getSubTopic(id: Int): ApiResult<List<SubTopic>> {
        return apiCall { serviceClient.getSubTopics(id) }

    }
}