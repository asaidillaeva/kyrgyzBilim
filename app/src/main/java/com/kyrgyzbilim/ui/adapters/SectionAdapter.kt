package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.sections.Section
import kotlinx.android.synthetic.main.item_sections.*
import kotlinx.android.synthetic.main.item_sections.view.*
import java.text.FieldPosition

class SectionAdapter(
    private val onClickListener: SectionClickListener
): ListAdapter<Section, SectionAdapter.SectionViewHolder>(DIFF) {

    companion object{
        private val DIFF = object : DiffUtil.ItemCallback<Section>(){
            override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class SectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        @SuppressLint("ResourceAsColor")
        fun onBind(position: Int){
            val currentSection = getItem(position)

            val themesRV = itemView.themes_RV
            val cardItem = itemView.section_item_card

            itemView.setOnClickListener{
                onClickListener.onClickSection(position)
            }

            itemView.section_title.text = currentSection.name
            itemView.section_item_card.setOnClickListener{
                if (themesRV.visibility == View.GONE){
                    themesRV.visibility = View.VISIBLE
                    cardItem.setCardBackgroundColor(R.color.mainColor)
                }
                else{
                    themesRV.visibility = View.GONE
                    cardItem.setCardBackgroundColor(R.color.white)


                }

            }

//            itemView.section_image.r = currentSection.image
        }
    }

    interface SectionClickListener{
        fun onClickSection(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
       return SectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sections, parent, false))
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.onBind(position)
    }
}