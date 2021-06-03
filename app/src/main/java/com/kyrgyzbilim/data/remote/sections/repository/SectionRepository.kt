package com.kyrgyzbilim.data.remote.sections.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.sections.Section
import com.kyrgyzbilim.data.remote.topic.Topic

interface SectionRepository {
    suspend fun getSectionsList(courseId: Int): ApiResult<List<Section>>
    suspend fun getTopic(id: Int): ApiResult<List<Topic>>


}