package com.kyrgyzbilim.ui.courses.sections

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.kyrgyzbilim.MainActivity
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.sections.Section
import com.kyrgyzbilim.ui.adapters.SectionAdapter
import com.kyrgyzbilim.ui.adapters.ThemesAdapter
import com.kyrgyzbilim.ui.courses.sections.themes.ThemesFragment
import com.kyrgyzbilim.ui.courses.sections.themes.dialog.DialogFragmentK
import com.kyrgyzbilim.ui.courses.sections.themes.text.TextFragment
import kotlinx.android.synthetic.main.fragment_sections.*

class SectionsFragment : Fragment(), SectionAdapter.SectionClickListener,
    ThemesAdapter.ThemesOnClickListener{

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
        initViews()

        loadSectionData()
    }

    private fun initViews() {
        go_back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack();
        }
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


//        loadThemesData()


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
        val current = themesAdapter.getItemId(position)
        Log.d("me", "theme clicked")

        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.slide_in_left,
                R.animator.slide_out_right, 0, 0)
            .replace(R.id.fragment_home, DialogFragmentK())
            .addToBackStack(null)
            .commit()
    }
}