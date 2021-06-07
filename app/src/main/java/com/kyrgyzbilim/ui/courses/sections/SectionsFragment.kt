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
import com.kyrgyzbilim.ui.adapters.SectionAdapter
import kotlinx.android.synthetic.main.fragment_sections.*


class SectionsFragment : Fragment() {

    private lateinit var sectionAdapter: SectionAdapter

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

        arguments?.let {
            val courseId = SectionsFragmentArgs.fromBundle(it).sectionId
            sectionViewModel.setCourseId(courseId)
        }
        getSection()
    }

    private fun getSection() {
        sectionViewModel.section.observe(viewLifecycleOwner) { that ->
            when (that) {
                is ApiResult.Success -> {
                    progress_bar.visibility = View.GONE
                    recyclerSection.visibility = View.VISIBLE
                    Log.e("Section Success", that.data.toString())

                    initList(that.data)
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


    private fun initList(sections: List<Section>?) {
        sectionAdapter = SectionAdapter(sections)
        recyclerSection.adapter = sectionAdapter
        sectionAdapter.submitList(sections)

        initViews()

    }

    private fun initViews() {
        go_back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack();
        }
    }
}