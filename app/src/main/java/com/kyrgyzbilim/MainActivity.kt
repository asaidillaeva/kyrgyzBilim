package com.kyrgyzbilim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kyrgyzbilim.data.UserData

class MainActivity : AppCompatActivity() {
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