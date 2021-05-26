package com.kyrgyzbilim.ui.courses

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.data.remote.course.Course
import com.kyrgyzbilim.databinding.FragmentCoursesBinding
import com.kyrgyzbilim.ui.adapters.CourseAdapter
import com.kyrgyzbilim.ui.courses.sections.SectionsFragment
import kotlinx.android.synthetic.main.fragment_courses.*


class CoursesFragment : Fragment(), CourseAdapter.CourseClickListener {
    private lateinit var binding: FragmentCoursesBinding

    companion object {
        const val COURSE_ID = "courseId"
        const val SECTION_NAME = "sectionName"
    }

    private lateinit var adapter: CourseAdapter

    private val courseViewModel: CourseViewModel by viewModels {
        InjectorObject.getCourseViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO: Try to uncomment lines bellow
//        binding = FragmentCoursesBinding.inflate(layoutInflater)
////        setContentView(binding.root)

//        return inflater.inflate(R.layout.fragment_courses, binding.root, false)
        return inflater.inflate(R.layout.fragment_courses,container, false)

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

    private fun initList(courseList: List<Course>) {

        adapter = CourseAdapter(this)
        recyclerCourse.adapter = adapter
        adapter.submitList(courseList)


        val layoutManager = LinearLayoutManager(activity)
        recyclerCourse.layoutManager = layoutManager
        adapter.notifyDataSetChanged()
        recyclerCourse.adapter = adapter

    }

    override fun onClickCourse(position: Int) {
        val current = adapter.getItemAtPos(position)

        val sectionsFragment: Fragment = SectionsFragment()

        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_out_right, 0, 0
            )
            .replace(R.id.fragment_home, sectionsFragment)
            .addToBackStack(null)
            .commit()

        val bundle = Bundle()
        bundle.putInt(COURSE_ID, current.id)
        sectionsFragment.arguments = bundle
    }


}