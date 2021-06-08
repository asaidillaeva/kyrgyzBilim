package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import kotlinx.android.synthetic.main.item_grammar.view.*

class GrammarAdapter : ListAdapter<SubTopic, GrammarAdapter.SubTopicViewHolder>(DIFF) {


    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<SubTopic>() {
            override fun areItemsTheSame(oldItem: SubTopic, newItem: SubTopic): Boolean {
                return oldItem.id == newItem.id
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: SubTopic, newItem: SubTopic): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class SubTopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val currentSection = getItem(position)

//            itemView.grammarTitle.text = item?.text
//            itemView.grammarTitleEn.text = item?.translated_text
            itemView.textBody.text = currentSection?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicViewHolder{
        return SubTopicViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_grammar, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: SubTopicViewHolder, position: Int) {
        viewHolder.onBind(position)
    }
}