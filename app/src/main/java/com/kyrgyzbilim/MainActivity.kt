package com.kyrgyzbilim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kyrgyzbilim.ui.courses.CoursesFragment

class MainActivity : AppCompatActivity() {

    private val fragmentManager: FragmentManager = supportFragmentManager

    private val coursesFragment: Fragment =  CoursesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        fragmentManager.beginTransaction()
                .add(android.R.id.content, coursesFragment)
//                .add(android.R.id.content, CoursesFragment::class.java.newInstance() as Fragment)
                .commit()



    }




}