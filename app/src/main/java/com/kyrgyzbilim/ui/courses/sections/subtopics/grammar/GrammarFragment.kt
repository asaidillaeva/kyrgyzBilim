package com.kyrgyzbilim.ui.courses.sections.subtopics.grammar

import android.annotation.SuppressLint
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
import com.kyrgyzbilim.data.UserData
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import com.kyrgyzbilim.ui.adapters.DialogVocabularyAdapter
import com.kyrgyzbilim.ui.adapters.GrammarAdapter
import com.kyrgyzbilim.ui.courses.sections.subtopics.SubTopicViewModel
import kotlinx.android.synthetic.main.fragment_grammar.*


class GrammarFragment : Fragment() {

    private val subTopicViewModel: SubTopicViewModel by viewModels {
        InjectorObject.getSubTopicViewModelFactory()
    }

    private lateinit var grammarAdapter: GrammarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grammar, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var topicTranslatedName = "text"
        var topicName = "text"

        val token = UserData.of(requireContext()).getToken()

        if (token != null || token != "") {
            arguments?.let {
                val args = GrammarFragmentArgs.fromBundle(it)
                val topicId = args.id
                topicName = args.name
                topicTranslatedName = args.translatedName
                subTopicViewModel.setTopic(topicId)
                if (token != null) {
                    subTopicViewModel.setToken(token)
                }
            }

            grammarTitle?.text = topicName
            if (topicTranslatedName.isNotEmpty()) {
                grammarTitleEn?.text = "/$topicTranslatedName"
            }

            val layoutManager = LinearLayoutManager(activity)
            val adapter = GrammarAdapter()
            recyclerGrammar.layoutManager = layoutManager
            recyclerGrammar.adapter = adapter
            subTopicViewModel.subTopic.observe(viewLifecycleOwner, {
                when (it) {
                    is ApiResult.Success -> {
                        grammar_progress_bar.visibility = View.GONE
                        recyclerGrammar.visibility = View.VISIBLE
                        initList(it.data)
                    }
                    is ApiResult.Error -> {
                        it.throwable.message.toString()
                        grammar_progress_bar.visibility = View.GONE
                        Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
                    }
                    is ApiResult.Loading -> {
                        grammar_progress_bar.visibility = View.VISIBLE
                    }
                    else -> {
                        grammar_progress_bar.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun initList(data: List<SubTopic>) {
        grammarAdapter = GrammarAdapter()
        recyclerGrammar.adapter = grammarAdapter
        val layoutManager = LinearLayoutManager(activity)
        recyclerGrammar.layoutManager = layoutManager
        grammarAdapter.submitList(data)

    }
}
