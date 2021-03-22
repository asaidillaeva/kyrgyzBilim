package com.kyrgyzbilim.ui.sections

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.sections.Section
import com.kyrgyzbilim.ui.adapters.CoursesAdapter
import com.kyrgyzbilim.ui.adapters.SectionAdapter
import kotlinx.android.synthetic.main.fragment_sections.*
import kotlinx.android.synthetic.main.item_sections.*

class SectionsFragment : Fragment(), SectionAdapter.SectionClickListener {

    private lateinit var adapter: SectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SectionAdapter(this)

        loadData()
    }

    private fun loadData() {

        val section = Section(1,"Dialog", 1)
        val section2 = Section(2,"Vocabulary", 1)
        val section3 = Section(3,"Text", 1)
        val section4 = Section(4,"Grammar", 1)

        val sectionsList = arrayListOf(section, section2, section3,section4)

        recyclerSection.adapter = adapter
        adapter.submitList(sectionsList)

    }

    @SuppressLint("ResourceAsColor")
    override fun onClickSection(position: Int) {
        val current = adapter.getItemId(position)

//        if (themes_RV.visibility == View.GONE){
//            themes_RV.visibility = View.VISIBLE
//            section_item_card.setCardBackgroundColor(R.color.mainColor)
//        }
//        else{
//            themes_RV.visibility = View.GONE
//            section_item_card.setCardBackgroundColor(R.color.white)
//
//        }

    }
}