package com.kyrgyzbilim.data.remote.course.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.apiCall
import com.kyrgyzbilim.data.remote.course.Course
import com.kyrgyzbilim.data.remote.ServiceClient

class CourseRepositoryImpl (
    private val serviceClient: ServiceClient
) : CourseRepository {
    override suspend fun getCourseList(token: String): ApiResult<List<Course>> {
        return apiCall { serviceClient.getCourses("Bearer $token") }
    }


}
