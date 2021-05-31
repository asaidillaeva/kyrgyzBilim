package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import kotlinx.android.synthetic.main.item_grammar.view.*

class GrammarAdapter : ListAdapter<SubTopic, GrammarAdapter.SubTopicHolder>(DIFF) {
    private  var items: List<SubTopic>? = null
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<SubTopic>() {
            override fun areItemsTheSame(oldItem: SubTopic, newItem: SubTopic): Boolean {
                return oldItem.id == newItem.id
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: SubTopic, newItem: SubTopic): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setData(
        items: List<SubTopic>?
    ) {
        this.items = items
    }

    inner class SubTopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val item = items?.get(position)
            itemView.grammarTitle.text = item?.name
            itemView.grammarTitleEn.text = item?.translated_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicHolder{
        return SubTopicHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_grammar, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubTopicHolder, position: Int) {
        holder.onBind(position)
    }
}