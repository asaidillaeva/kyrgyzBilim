package com.kyrgyzbilim.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.themes.Theme
import kotlinx.android.synthetic.main.item_sections.view.*
import kotlinx.android.synthetic.main.item_themes.view.*
import java.text.FieldPosition


class ThemesAdapter(
    val onClickListener: ThemesOnClickListener
) : ListAdapter<Theme, ThemesAdapter.ThemeViewHolder>(DIFF) {

    companion object{
        val DIFF  = object: DiffUtil.ItemCallback<Theme>(){
            override fun areItemsTheSame(oldItem: Theme, newItem: Theme): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Theme, newItem: Theme): Boolean {
                return oldItem == newItem

            }

        }
    }

    inner class ThemeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(position: Int){
            val currentTheme = getItem(position)

            val themesRV = itemView.themes_RV

            itemView.theme_title.text = currentTheme.title
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