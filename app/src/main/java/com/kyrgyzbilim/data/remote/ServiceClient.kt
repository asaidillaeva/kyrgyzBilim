package com.kyrgyzbilim.data.remote

import com.google.gson.GsonBuilder
import com.kyrgyzbilim.data.remote.course.Course
import com.kyrgyzbilim.data.remote.sections.Section
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import com.kyrgyzbilim.data.remote.topic.Topic
import com.kyrgyzbilim.data.remote.user.*
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
        private const val baseUtl = "http://159.89.29.83"

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
    suspend fun  login( @Body adv: LoginRequestBody): LoginResponse

    @Headers("Content-Type: multipart/form-data")
    @FormUrlEncoded
    @POST("/v1/auth/register")
    suspend fun  register( @Body a: RegisterRequestBody): RegisterResponse

    @GET("/v1/user/")
    suspend fun  getUser(): User

    @GET("/v1/courses")
    suspend fun  getCourses(): List<Course>

    @GET("/v1/courses/{id}/sections")
    suspend fun  getSection(@Path("id") id: Int): List<Section>

    @GET("/v1/sections/{id}/topics")
    suspend fun getTopics(@Path("id") id: Int): List<Topic>

    @GET("/v1/topics/{id}")
    suspend fun getSubTopics(@Path("id") id: Int): List<SubTopic>



}