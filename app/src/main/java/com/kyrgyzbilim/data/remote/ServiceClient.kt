package com.kyrgyzbilim.data.remote

import com.google.gson.GsonBuilder
import com.kyrgyzbilim.data.course.Course
import com.kyrgyzbilim.data.sections.Section
import com.kyrgyzbilim.data.themes.Topic
import com.kyrgyzbilim.data.user.LoginRequestBody
import com.kyrgyzbilim.data.user.LoginResponse
import com.kyrgyzbilim.data.user.RegisterResponse
import com.kyrgyzbilim.data.user.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ServiceClient {

    companion object {
        private const val baseUtl = "http://164.90.161.152"

        private fun getGson() = GsonBuilder().setLenient().create()

        private fun getLogger() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private fun getOkHttp() = OkHttpClient.Builder()
            .addInterceptor(getLogger())
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(baseUtl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build()

        operator fun invoke() = getRetrofit().create(ServiceClient::class.java)
    }

    @POST("v1/auth/register")
    suspend fun listCategories(): Response<RegisterResponse>

    @POST("/v1/auth/login")
    suspend fun  login( @Body adv: LoginRequestBody): Response<LoginResponse>

    @GET("/v1/user/")
    suspend fun  getUser(): Response<User>

    @GET("/v1/courses")
    suspend fun  getCourses(): List<Course>

    @GET("/v1/courses/{id}/sections")
    suspend fun  getSection(@Path("id") courseId: Int): List<Section>

    @GET("/v1/topics/{id}")
    suspend fun getSubTopics(@Path("id") courseId: Int): Response<Section>

    @GET("/v1/sections/{id}/topics")
    suspend fun getTopics(@Path("id") courseId: Int): Response<Topic>


}