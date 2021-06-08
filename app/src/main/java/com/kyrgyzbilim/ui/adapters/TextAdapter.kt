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
import kotlinx.android.synthetic.main.item_text.view.*

class TextAdapter : ListAdapter<SubTopic, TextAdapter.SubTopicHolder>(DIFF) {

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


    inner class SubTopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val currentSection = getItem(position)
//            itemView.textTitle.text = item?.text
//            itemView.textTitleEn.text = item?.translated_text
            itemView.textBody.text = currentSection?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicHolder{
        return SubTopicHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubTopicHolder, position: Int) {
        holder.onBind(position)
    }
}