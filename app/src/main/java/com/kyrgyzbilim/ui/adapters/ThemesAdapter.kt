package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.themes.Topic
import kotlinx.android.synthetic.main.item_sections.view.*
import kotlinx.android.synthetic.main.item_themes.view.*


class ThemesAdapter(
    val onClickListener: ThemesOnClickListener
) : ListAdapter<Topic, ThemesAdapter.ThemeViewHolder>(DIFF) {

    companion object{
        val DIFF  = object: DiffUtil.ItemCallback<Topic>(){
            override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ThemeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(position: Int){
            val currentTheme = getItem(position)

            val themesRV = itemView.themes_RV

            itemView.theme_title.text = currentTheme.name
            itemView.amount_of_words.text = currentTheme.amountOfWords.toString()

            itemView.setOnClickListener{
                onClickListener.onClickTheme(position)
            }

        }
    }


    interface ThemesOnClickListener {
        fun onClickTheme(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        return ThemeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_themes,parent,false))
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.onBind(position)
    }
}