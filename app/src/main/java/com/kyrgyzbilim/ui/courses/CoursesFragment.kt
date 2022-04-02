package com.kyrgyzbilim.ui.courses

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.data.UserData
import com.kyrgyzbilim.data.remote.course.Course
import com.kyrgyzbilim.ui.adapters.CoursesAdapter
import com.kyrgyzbilim.ui.authorization.AuthActivity
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlin.concurrent.thread

class CoursesFragment : Fragment() {

    private lateinit var adapter: CoursesAdapter

    private val courseViewModel: CourseViewModel by viewModels {
        InjectorObject.getCourseViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_courses, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseViewModel.course.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Success -> {
                    progress_bar.visibility = View.GONE
                    recyclerCourse.visibility = View.VISIBLE
                    initList(it.data)
                }
                is ApiResult.Error -> {
                    it.throwable.message.toString()
                    Log.e("Course Error", it.throwable.message.toString())
                }
                is ApiResult.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    recyclerCourse.visibility = View.GONE
                }
            }
        }

        logout.setOnClickListener {
            UserData.of(requireContext()).saveToken("")
            startActivity(Intent(activity, AuthActivity::class.java))
            thread {
                Thread.sleep(500)
                activity?.finish()
            }
        }

        val token = UserData.of(requireContext()).getToken()

        if (token != null || token != "") {
            courseViewModel.user(token!!).observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResult.Success -> {
                        user_name.text = "${it.data.first_name} ${it.data.last_name}"
                    }
                    is ApiResult.Error -> {
                        user_name.text = "error while loading user info"
                    }
                    is ApiResult.Loading -> {
                        user_name.text = "loading user info..."
                    }
                }
            }
        }
    }

    private fun initList(courseList: List<Course>) {
        adapter = CoursesAdapter()
        recyclerCourse.adapter = adapter
        adapter.submitList(courseList)
        val layoutManager = LinearLayoutManager(activity)
        recyclerCourse.layoutManager = layoutManager
        adapter.notifyDataSetChanged()
        recyclerCourse.adapter = adapter
    }
}
