package com.kyrgyzbilim.data.sections

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.course.Course

interface SectionRepository {
    suspend fun getSectionsList(courseId: Int): ApiResult<List<Section>>

}