package com.kyrgyzbilim.ui.courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.course.Course
import com.kyrgyzbilim.ui.adapters.CoursesAdapter
import com.kyrgyzbilim.ui.courses.sections.SectionsFragment
import kotlinx.android.synthetic.main.fragment_courses.*


class CoursesFragment : Fragment(), CoursesAdapter.CoursesClickListener {

    private lateinit var adapter: CoursesAdapter

//    private val mainViewModel: CourseViewModel by viewModels {
//        InjectorObject.getMainViewModelFactory()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCourse.hasFixedSize()
        adapter = CoursesAdapter(this)

        loadData()

    }

    private fun loadData() {
//        val course = сourseViewModel.getCourse()
        val courseFakeList = arrayListOf(
            Course(1,"Beginner","When an application component starts and the application does not have any other components running, the Android system", 20),
            Course(2,"Elementary","When an application component starts and the application does not have any other components running, the Android system", 20)
        )
        recyclerCourse.adapter = adapter
        adapter.submitList(courseFakeList)
    }

    override fun onClickCourse(position: Int) {

        val current = adapter.getItemId(position)

        val sectionsFragment: Fragment =  SectionsFragment()

        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.slide_in_left,
                R.animator.slide_out_right, 0, 0)
            .replace(R.id.fragment_home, sectionsFragment)
            .addToBackStack(null)
            .commit()
    }
}