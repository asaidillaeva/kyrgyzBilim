package com.kyrgyzbilim.data

import android.content.Context
import com.google.gson.Gson
import com.kyrgyzbilim.data.remote.user.User

class UserData(var context: Context){
    private val gson = Gson()
    private val prefsNode = "prefs"
    private val tokenNode = "token"

    fun getToken(): String? {
        val myPrefs = context.getSharedPreferences(prefsNode, Context.MODE_PRIVATE)
        return myPrefs.getString(tokenNode, null)
    }

    fun saveToken(token: String){
        val myPrefs = context.getSharedPreferences(prefsNode, Context.MODE_PRIVATE).edit()
        myPrefs.putString(tokenNode, token)
        myPrefs.apply()
    }

    companion object {
        fun of(context: Context): UserData {
            return UserData(context)
        }
    }
}