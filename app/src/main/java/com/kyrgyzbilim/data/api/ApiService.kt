package com.kyrgyzbilim.data.api

import com.kyrgyzbilim.data.api.models.*
import com.kyrgyzbilim.data.course.Course
import com.kyrgyzbilim.data.themes.Topic
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("v1/auth/register")
    suspend fun listCategories(): Response<RegisterResponse>

    @POST("/v1/auth/login")
    suspend fun  login( @Body adv: LoginRequestBody): Response<LoginResponse>

    @GET("/v1/user/")
    suspend fun  getUser(): Response<User>

    @GET("/v1/courses")
    suspend fun  getCourses(): Response<Course>

    @GET("/v1/courses/")
    suspend fun  getCourseById(@Query("courseId") courseId: Int): Response<Course>

    @GET("/v1/courses/:{courseId}/sections")
    suspend fun  getSection(@Query("courseId") courseId: Int): Response<Section>

    @GET("/v1/courses/:{courseId}/sections/:{sectionId}")
    suspend fun getSectionById(@Query("courseId") courseId: Int, @Query("sectionId") sectionId: Int): Response<Section>

    @GET("/v1/courses/:{courseId}/sections/:{sectionId}/topics")
    suspend fun getTopics(@Query("courseId") courseId: Int,@Query("sectionId") sectionId: Int): Response<Topic>


}