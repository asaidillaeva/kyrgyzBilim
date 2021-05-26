package com.kyrgyzbilim.ui.courses.sections

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.data.remote.sections.Section
import com.kyrgyzbilim.data.remote.topic.Topic
import com.kyrgyzbilim.ui.adapters.SectionAdapter
import com.kyrgyzbilim.ui.adapters.ThemesAdapter
import com.kyrgyzbilim.ui.courses.CoursesFragment
import com.kyrgyzbilim.ui.courses.sections.subtopics.dialog.DialogFragmentK
import kotlinx.android.synthetic.main.fragment_sections.*


class SectionsFragment : Fragment(), SectionAdapter.SectionClickListener,
    ThemesAdapter.ThemesOnClickListener {

    private lateinit var sectionAdapter: SectionAdapter
    private lateinit var themesAdapter: ThemesAdapter
    private var defaultValue: Int = -1
    private var sectionList: List<Section>? = null
    private var topicList: List<Topic>? = null

    private val sectionViewModel: SectionViewModel by viewModels {
        InjectorObject.getSectionViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            val courseId = bundle.getInt(CoursesFragment.COURSE_ID, defaultValue)
            sectionViewModel.setCourseId(courseId)

        }


        getSection()

    }


     fun getSection() {
        var topicListIn: List<Topic>? = null

        sectionViewModel.section.observe(viewLifecycleOwner) { that ->
            when (that) {
                is ApiResult.Success -> {
                    progress_bar.visibility = View.GONE
                    recyclerSection.visibility = View.VISIBLE
                    Log.e("Section Success", that.data.toString())

//                    sectionViewModel.topics.observe(viewLifecycleOwner) {
//                        when (it) {
//                            is ApiResult.Success -> {
//                                progress_bar.visibility = View.GONE
//                                recyclerSection.visibility = View.VISIBLE
//                                topicListIn = it.data
//                                Log.e("Topic Success", it.data.toString())
//
//                            }
//                            is ApiResult.Error -> {
//                                it.throwable.message.toString()
//                                Log.e("Course Error", it.throwable.message.toString())
//                            }
//                            is ApiResult.Loading -> {
//                                progress_bar.visibility = View.VISIBLE
//                                recyclerSection.visibility = View.GONE
//                            }
//
//                        }

//                        initList(that.data, topicListIn,sectionViewModel)
                        initList(that.data, topicListIn)

//                    }

                }
                is ApiResult.Error -> {
                    that.throwable.message.toString()
                    Log.e("Section Error", that.throwable.message.toString())
                }
                is ApiResult.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    recyclerSection.visibility = View.GONE
                }
            }


        }


    }


    private fun initList(sections: List<Section>?, topics: List<Topic>?) {

        sectionAdapter = SectionAdapter(this, this, sections)

        themesAdapter = ThemesAdapter()
        themesAdapter.setData(this, topics)


        recyclerSection.adapter = sectionAdapter
        sectionAdapter.submitList(sections)

        initViews()

    }

    private fun initViews() {
        go_back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack();
        }
    }


    override fun onClickSection(position: Int) {

    }

    override fun onClickTheme(position: Int) {
        val current = themesAdapter.getItemAtPos(position)

        Log.d("me", "theme clicked")

        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_out_right, 0, 0
            )
            .replace(R.id.fragment_home, DialogFragmentK())
            .addToBackStack(null)
            .commit()

        val fragment = SectionsFragment()
        val bundle = Bundle()
        bundle.putInt(CoursesFragment.COURSE_ID, current.id)
        fragment.arguments = bundle
    }
}