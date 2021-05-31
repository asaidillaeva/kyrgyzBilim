package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.remote.sections.Section
import com.kyrgyzbilim.data.remote.topic.Topic
import com.kyrgyzbilim.ui.courses.sections.SectionsFragmentDirections
import kotlinx.android.synthetic.main.item_themes.view.*


class ThemesAdapter : ListAdapter<Topic, ThemesAdapter.ThemeViewHolder>(DIFF) {
    private  var items: List<Topic>? = null
    private  var sectionType: String? = null

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
        sectionType: String?
    ) {
            this.items = items
            this.sectionType = sectionType
    }

    inner class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val currentTheme = getItem(position)

            itemView.theme_title.text = currentTheme.name

            itemView.themeRV_item.setOnClickListener {
                Log.e("clicek", sectionType.toString())
                val action = when (sectionType) {
                    "Vocabulary" ->
                        SectionsFragmentDirections.actionSectionsFragmentToVocabularyFragment(currentTheme.id)
                    "Dialogs" ->
                        SectionsFragmentDirections.actionSectionsFragmentToDialogFragmentK(currentTheme.id)
                    "Texts" ->
                        SectionsFragmentDirections.actionSectionsFragmentToTextFragment(currentTheme.id)
                    "Grammar" ->
                        SectionsFragmentDirections.actionSectionsFragmentToGrammarFragment(currentTheme.id)
                    else -> SectionsFragmentDirections.actionSectionsFragmentToErrorFragment()

                }

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