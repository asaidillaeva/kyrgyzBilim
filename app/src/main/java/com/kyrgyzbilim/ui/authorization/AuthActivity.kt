package com.kyrgyzbilim.ui.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.kyrgyzbilim.R
import com.kyrgyzbilim.ui.courses.CoursesFragmentDirections

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}