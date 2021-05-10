package com.kyrgyzbilim.ui.courses

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.base.OnItemClickListener
import com.kyrgyzbilim.data.course.Course
import com.kyrgyzbilim.ui.adapters.CourseAdapter
import com.kyrgyzbilim.ui.courses.sections.SectionsFragment
import kotlinx.android.synthetic.main.fragment_courses.*


class CoursesFragment : Fragment() {

    private lateinit var adapter: CourseAdapter

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


    }

    private fun initList(characters: List<Course>) {

        adapter = CourseAdapter(characters, object :
            OnItemClickListener {
            override fun <T> onItemClick(listItem: T) {
                val sectionsFragment: Fragment =  SectionsFragment()

                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                fragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_left,
                        R.animator.slide_out_right, 0, 0)
                    .replace(R.id.fragment_home, sectionsFragment)
                    .addToBackStack(null)
                    .commit()
            }

        })

        val layoutManager = LinearLayoutManager(activity)
        recyclerCourse.layoutManager = layoutManager
        adapter.notifyDataSetChanged()
        recyclerCourse.adapter = adapter

    }


}