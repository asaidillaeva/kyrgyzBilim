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
import androidx.fragment.app.viewModels
import com.kyrgyzbilim.MainActivity
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.data.sections.Section
import com.kyrgyzbilim.ui.adapters.SectionAdapter
import com.kyrgyzbilim.ui.adapters.ThemesAdapter
import com.kyrgyzbilim.ui.courses.CourseViewModel
import com.kyrgyzbilim.ui.courses.sections.themes.ThemesFragment
import com.kyrgyzbilim.ui.courses.sections.themes.dialog.DialogFragmentK
import com.kyrgyzbilim.ui.courses.sections.themes.text.TextFragment
import kotlinx.android.synthetic.main.fragment_sections.*

class SectionsFragment : Fragment(), SectionAdapter.SectionClickListener,
    ThemesAdapter.ThemesOnClickListener{

    private lateinit var sectionAdapter: SectionAdapter
    private lateinit var themesAdapter: ThemesAdapter

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

        sectionViewModel.section.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Success -> {
                    progress_bar.visibility = View.GONE
                    recyclerSection.visibility = View.VISIBLE
                    initList(it.data)
                }
                is ApiResult.Error -> {
                    it.throwable.message.toString()
                    Log.e("Course Error", it.throwable.message.toString())
                }
                is ApiResult.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    recyclerSection.visibility = View.GONE
                }
            }

        }


    }

    private fun initList(sections: List<Section>){

        sectionAdapter = SectionAdapter(this,this)
        themesAdapter = ThemesAdapter(this)
        initViews()

        recyclerSection.adapter = sectionAdapter
        sectionAdapter.submitList(sections)

//        loadSectionData()
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