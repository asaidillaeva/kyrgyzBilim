package com.kyrgyzbilim.data.api

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyrgyzbilim.data.api.models.LoginRequestBody
import com.kyrgyzbilim.data.api.models.LoginResponse
import com.kyrgyzbilim.data.api.models.Section
import com.kyrgyzbilim.data.api.models.User
import com.kyrgyzbilim.data.course.Course
import com.kyrgyzbilim.data.themes.Topic
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.EOFException
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class APIManager(var service: ApiService) {

    fun login(login: String, password: String): LiveData<LoginResponse?> {
        val list = MutableLiveData<LoginResponse?>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = service.login(LoginRequestBody(login, password))
                withContext(Dispatchers.Main) {
                    list.value = response.body()
                }
            } catch (e: Exception) {
                if (e is EOFException) {
                    withContext(Dispatchers.Main) {
                        list.value = null
                    }
                }
            }
        }
        return list
    }

    fun getCourses(): LiveData<Course?> {
        val list = MutableLiveData<Course?>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = service.getCourses()
                withContext(Dispatchers.Main) {
                    list.value = response.body()
                }
            } catch (e: Exception) {
                if (e is EOFException) {
                    withContext(Dispatchers.Main) {
                        list.value = null
                    }
                }
            }
        }
        return list
    }

    //it is not list, it is an object
    fun getUser(): LiveData<User?> {
        val list = MutableLiveData<User?>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = service.getUser()
                withContext(Dispatchers.Main) {
                    list.value = response.body()
                }
            } catch (e: Exception) {
                if (e is EOFException) {
                    withContext(Dispatchers.Main) {
                        list.value = null
                    }
                }
            }
        }
        return list
    }

    fun getSection(courseId: Int): LiveData<Section?> {
        val list = MutableLiveData<Section?>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = service.getSection(courseId)
                withContext(Dispatchers.Main) {
                    list.value = response.body()
                }
            } catch (e: Exception) {
                if (e is EOFException) {
                    withContext(Dispatchers.Main) {
                        list.value = null
                    }
                }
            }
        }
        return list
    }

    fun getSectionById(courseId: Int, sectionId: Int): LiveData<Section?> {
        val list = MutableLiveData<Section?>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = service.getSectionById(courseId, sectionId)
                withContext(Dispatchers.Main) {
                    list.value = response.body()
                }
            } catch (e: Exception) {
                if (e is EOFException) {
                    withContext(Dispatchers.Main) {
                        list.value = null
                    }
                }
            }
        }
        return list
    }

    fun getTopics(courseId: Int, sectionId: Int): LiveData<Topic?> {
        val list = MutableLiveData<Topic?>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = service.getTopics(courseId, sectionId)
                withContext(Dispatchers.Main) {
                    list.value = response.body()
                }
            } catch (e: Exception) {
                if (e is EOFException) {
                    withContext(Dispatchers.Main) {
                        list.value = null
                    }
                }
            }
        }
        return list
    }

    fun getCourseById(courseId: Int): LiveData<Course?> {
        val list = MutableLiveData<Course?>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = service.getCourseById(courseId)
                withContext(Dispatchers.Main) {
                    list.value = response.body()
                }
            } catch (e: Exception) {
                if (e is EOFException) {
                    withContext(Dispatchers.Main) {
                        list.value = null
                    }
                }
            }
        }
        return list
    }


    companion object {
        val BASE_URL = "http://167.99.142.21/"

        fun create(): APIManager {
            val service = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
            return APIManager(service)
        }
    }
}