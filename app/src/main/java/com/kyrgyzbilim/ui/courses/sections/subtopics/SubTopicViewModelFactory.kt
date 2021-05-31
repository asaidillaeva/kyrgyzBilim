package com.kyrgyzbilim.ui.courses.sections.subtopics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyrgyzbilim.data.remote.subTopic.SubTopicRepository
import com.kyrgyzbilim.ui.courses.sections.SectionViewModel

class SubTopicViewModelFactory(
    private val subTopicRepository: SubTopicRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SubTopicRepository::class.java) ->
                SubTopicViewModel(subTopicRepository) as T
            else -> throw IllegalArgumentException("Not found")
        }
    }
}