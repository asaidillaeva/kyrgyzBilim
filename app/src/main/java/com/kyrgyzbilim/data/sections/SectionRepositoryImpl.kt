package com.kyrgyzbilim.data.sections

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.apiCall
import com.kyrgyzbilim.data.remote.ServiceClient

class SectionRepositoryImpl(
    private val serviceClient: ServiceClient
): SectionRepository{
    override suspend fun getSectionsList(courseId: Int): ApiResult<List<Section>> {
        return apiCall { serviceClient.getSection(courseId) }

    }
}