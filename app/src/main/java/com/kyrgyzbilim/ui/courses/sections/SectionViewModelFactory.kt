package com.kyrgyzbilim.ui.courses.sections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyrgyzbilim.data.remote.sections.SectionRepository

class SectionViewModelFactory (
    private val sectionRepository: SectionRepository
        ): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SectionViewModel::class.java) ->
                SectionViewModel(sectionRepository) as T
            else -> throw IllegalArgumentException("Not found")
        }
    }
}