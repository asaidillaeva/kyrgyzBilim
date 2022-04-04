package com.kyrgyzbilim.ui.courses.sections.subtopics.dialog

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.base.OnItemClickListener
import com.kyrgyzbilim.data.UserData
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import com.kyrgyzbilim.ui.adapters.DialogVocabularyAdapter
import com.kyrgyzbilim.ui.courses.sections.subtopics.SubTopicViewModel
import kotlinx.android.synthetic.main.fragment_dialog.*

class DialogFragment : Fragment() {

    private val subTopicViewModel: SubTopicViewModel by viewModels {
        InjectorObject.getSubTopicViewModelFactory()
    }

    private lateinit var dialogVocabularyAdapter: DialogVocabularyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var topicTranslatedName = "text"
        var topicName = "text"

        val token = UserData.of(requireContext()).getToken()

        if (token != null || token != "") {
            arguments?.let {
                val args = DialogFragmentArgs.fromBundle(it)
                val topicId = args.id
                topicName = args.name
                topicTranslatedName = args.translatedName
                subTopicViewModel.setTopic(topicId)
                if (token != null) {
                    subTopicViewModel.setToken(token)
                }
            }
        }

        dialogTitle.text = topicName
        if (topicTranslatedName.isNotEmpty()) {
            dialogTitleEn.text = "/$topicTranslatedName"
        }


        subTopicViewModel.subTopic.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Success -> {
                    progress_bar.visibility = View.GONE
                    recyclerDialog.visibility = View.VISIBLE
                    Log.e("Section Success", it.data.toString())
                    initList(it.data)
                }
                is ApiResult.Error -> {
                    it.throwable.message.toString()
                    Log.e("Section Error", it.throwable.message.toString())
                }
                is ApiResult.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    recyclerDialog.visibility = View.GONE
                }
            }
        }


    }

    private fun initList(data: List<SubTopic>) {
        dialogVocabularyAdapter = DialogVocabularyAdapter()
        recyclerDialog.adapter = dialogVocabularyAdapter
        val layoutManager = LinearLayoutManager(activity)
        recyclerDialog.layoutManager = layoutManager
        val filteredData = data.sortedBy { it.id }
        dialogVocabularyAdapter.submitList(filteredData)
        dialogVocabularyAdapter.setData(context)

    }

}
