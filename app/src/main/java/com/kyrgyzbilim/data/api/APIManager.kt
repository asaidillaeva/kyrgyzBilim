package com.kyrgyzbilim.data.api

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyrgyzbilim.data.api.models.LoginRequestBody
import com.kyrgyzbilim.data.api.models.LoginResponse
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.EOFException
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class APIManager (var service: ApiService) {

    fun login(login: String, password: String): LiveData<LoginResponse?> {
        val list = MutableLiveData<LoginResponse?>()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val response = service.login(LoginRequestBody(login, password))
                withContext(Dispatchers.Main){
                    list.value = response.body()
                }
            }catch (e: Exception){
                if(e is EOFException){
                    withContext(Dispatchers.Main){
                        list.value = null
                    }
                }
            }
        }
        return list
    }

    companion object{
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