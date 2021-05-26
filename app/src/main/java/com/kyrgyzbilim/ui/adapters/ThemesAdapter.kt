package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.remote.topic.Topic
import com.kyrgyzbilim.ui.courses.sections.SectionsFragmentDirections
import kotlinx.android.synthetic.main.item_themes.view.*


class ThemesAdapter : ListAdapter<Topic, ThemesAdapter.ThemeViewHolder>(DIFF) {
    private lateinit var items: List<Topic>

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Topic>() {
            override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setData(
        items: List<Topic>?,
    ) {
        if (items != null) {
            this.items = items
        }
    }

    inner class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val currentTheme = getItem(position)

            itemView.theme_title.text = currentTheme.name

            itemView.themeRV_item.setOnClickListener {
                val action =
                    SectionsFragmentDirections.actionSectionsFragmentToVocabularyFragment()
                val nav = Navigation.findNavController(it)

                nav.navigate(action)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        return ThemeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_themes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.onBind(position)
    }
}