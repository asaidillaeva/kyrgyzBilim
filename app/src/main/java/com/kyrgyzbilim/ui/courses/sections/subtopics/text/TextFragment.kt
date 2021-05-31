package com.kyrgyzbilim.ui.courses.sections.subtopics.text

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.ui.adapters.TextAdapter
import com.kyrgyzbilim.ui.courses.sections.subtopics.SubTopicViewModel
import kotlinx.android.synthetic.main.fragment_text.*


class TextFragment : Fragment() {

    private val subTopicViewModel: SubTopicViewModel by viewModels {
        InjectorObject.getSubTopicViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        val adapter = TextAdapter()
        recyclerText.layoutManager = layoutManager
        recyclerText.adapter = adapter
        subTopicViewModel.subTopic.observe(viewLifecycleOwner, {
            when (it) {
                is ApiResult.Success -> {
                    text_progress_bar.visibility = View.GONE
                    recyclerText.visibility = View.VISIBLE
                    adapter.setData(it.data)
                }
                is ApiResult.Error -> {
                    it.throwable.message.toString()
                    text_progress_bar.visibility = View.GONE
                    Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
                }
                is ApiResult.Loading -> {
                    text_progress_bar.visibility = View.VISIBLE
                }
                else -> {
                    text_progress_bar.visibility = View.VISIBLE
                }
            }
        })
    }
}