package com.kyrgyzbilim.ui.themesSection

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kyrgyzbilim.ui.themesSection.grammar.GrammarFragment
import com.kyrgyzbilim.ui.themesSection.text.TextFragment
import com.kyrgyzbilim.ui.themesSection.vocabulary.VocabularyFragment

class ThemesPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!)  {

    override fun getPageTitle(position: Int): CharSequence? {
        return  when (position) {
            0 -> "Dialog"
            1 -> "Vocabulary"
            2 -> "Text"
            else -> "Grammar"
        }

    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DialogFragment()
            1 -> VocabularyFragment()
            2 -> TextFragment()
            else -> GrammarFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }
}