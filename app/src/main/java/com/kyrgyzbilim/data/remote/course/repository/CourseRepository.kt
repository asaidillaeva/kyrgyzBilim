package com.kyrgyzbilim.data.remote.course.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.course.Course

interface CourseRepository {
    suspend fun getCourseList(): ApiResult<List<Course>>

}