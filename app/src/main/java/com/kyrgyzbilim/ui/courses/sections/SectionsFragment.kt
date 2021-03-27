package com.kyrgyzbilim.ui.courses.sections

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.sections.Section
import com.kyrgyzbilim.data.themes.Theme
import com.kyrgyzbilim.ui.adapters.SectionAdapter
import com.kyrgyzbilim.ui.adapters.ThemesAdapter
import kotlinx.android.synthetic.main.fragment_sections.*
import kotlinx.android.synthetic.main.item_sections.*

class SectionsFragment : Fragment(), SectionAdapter.SectionClickListener, ThemesAdapter.ThemesOnClickListener {

    private lateinit var sectionAdapter: SectionAdapter
    private lateinit var themesAdapter: ThemesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionAdapter = SectionAdapter(this,this)
        themesAdapter = ThemesAdapter(this)

        loadSectionData()
    }

    private fun loadThemesData() {
        val themes = Theme(1,"Greeting", 120)
        val themes1 = Theme(2,"Items", 10)
        val themes2 = Theme(3,"One My Day", 145)
        val themes3 = Theme(4,"My Profession", 112)

        val themesList = arrayListOf(themes,themes1, themes2, themes3)

        themes_RV?.adapter = themesAdapter
        themesAdapter.submitList(themesList)
    }

    private fun loadSectionData() {

        val section = Section(1,"Dialog", 1)
        val section2 = Section(2,"Vocabulary", 1)
        val section3 = Section(3,"Text", 1)
        val section4 = Section(4,"Grammar", 1)

        val sectionsList = arrayListOf(section, section2, section3,section4)

        recyclerSection.adapter = sectionAdapter
        sectionAdapter.submitList(sectionsList)

    }

    @SuppressLint("ResourceAsColor")
    override fun onClickSection(position: Int) {
        val current = sectionAdapter.getItemId(position)
        loadThemesData()


//        section_item_card.setCardBackgroundColor(Color.parseColor("#b70505"))
//        section_item_card.setCardBackgroundColor(R.color.mainColor)
//        Log.e("clicked",current.toString())

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

    override fun onClickTheme(position: Int) {

    }
}