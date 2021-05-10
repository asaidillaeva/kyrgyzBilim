package com.kyrgyzbilim.data.course.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.course.Course

interface CourseRepository {
    suspend fun getCourseList(): ApiResult<List<Course>>

}