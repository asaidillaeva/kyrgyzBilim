package com.kyrgyzbilim.ui.courses.sections.subtopics.dialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import com.kyrgyzbilim.ui.courses.sections.subtopics.SubTopicViewModel
import kotlinx.android.synthetic.main.fragment_sections.*

class DialogFragment : Fragment() {

    private val subTopicViewModel: SubTopicViewModel by viewModels {
        InjectorObject.getSectionViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val topicId = DialogFragmentKArgs.fromBundle(it).id
            subTopicViewModel.setTopic(topicId)
        }

        subTopicViewModel.subTopic.observe(viewLifecycleOwner){it ->
            when (it) {
                is ApiResult.Success -> {
                    progress_bar.visibility = View.GONE
                    recyclerSection.visibility = View.VISIBLE
                    Log.e("Section Success", it.data.toString())
                    initList(it.data)
                }
                is ApiResult.Error -> {
                    it.throwable.message.toString()
                    Log.e("Section Error", it.throwable.message.toString())
                }
                is ApiResult.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    recyclerSection.visibility = View.GONE
                }
            }
        }


    }

    private fun initList(data: List<SubTopic>) {

    }
}