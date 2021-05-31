package com.kyrgyzbilim

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.kyrgyzbilim.data.UserData
import com.kyrgyzbilim.ui.authorization.AuthActivity
import com.kyrgyzbilim.ui.courses.CoursesFragmentDirections

val TAG = "me"

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // checking login
        val token = UserData.of(this).getToken()

//        if(token == null){
//            // not logged in
//            startActivity(Intent(this, AuthActivity::class.java))
//            Thread{
//                Thread.sleep(500)
//                finish()
//            }.start()
//        }
    }
}