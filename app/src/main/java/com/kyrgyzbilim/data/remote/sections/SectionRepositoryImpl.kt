package com.kyrgyzbilim.data.remote.sections

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.apiCall
import com.kyrgyzbilim.data.remote.ServiceClient
import com.kyrgyzbilim.data.remote.topic.Topic

class SectionRepositoryImpl(
    private val serviceClient: ServiceClient
): SectionRepository{
    override suspend fun getSectionsList(courseId: Int): ApiResult<List<Section>> {
        return apiCall { serviceClient.getSection(courseId) }

    }
    override suspend fun getTopic(id: Int): ApiResult<List<Topic>> {
        return apiCall { serviceClient.getTopics(id) }
    }
}