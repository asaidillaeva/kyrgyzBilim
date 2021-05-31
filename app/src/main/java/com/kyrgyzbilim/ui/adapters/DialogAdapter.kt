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
import kotlinx.android.synthetic.main.item_dialog.view.*

class DialogAdapter (private var subTopicList: List<SubTopic>?
) : ListAdapter<SubTopic, DialogAdapter.SubTopicViewHolder>(DIFF) {

    private lateinit var context: Context

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<SubTopic>() {
            override fun areItemsTheSame(oldItem: SubTopic, newItem: SubTopic): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SubTopic, newItem: SubTopic): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class SubTopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor")
        fun onBind(position: Int) {
            val currentSection = getItem(position)

            itemView.dialog.text = currentSection.text
            itemView.translationDialog.text = currentSection.translatedText

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicViewHolder {
        context = parent.context;
        return SubTopicViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_dialog, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubTopicViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun getItemAtPos(position: Int): SubTopic {
        return getItem(position)
    }

}