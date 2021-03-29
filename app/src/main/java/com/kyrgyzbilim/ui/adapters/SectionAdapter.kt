package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.sections.Section
import com.kyrgyzbilim.data.themes.Theme
import com.kyrgyzbilim.ui.courses.sections.SectionsFragment
import com.kyrgyzbilim.ui.courses.sections.themes.ThemesFragment
import kotlinx.android.synthetic.main.item_sections.*
import kotlinx.android.synthetic.main.item_sections.view.*

class SectionAdapter(
    private val onClickListener: SectionClickListener,
    private val fragment: SectionsFragment
): ListAdapter<Section, SectionAdapter.SectionViewHolder>(DIFF), ThemesAdapter.ThemesOnClickListener {

    private lateinit var context: Context
    private var themesAdapter = ThemesAdapter(fragment)



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
            val cardItem = itemView.section_background

            itemView.setOnClickListener{
                onClickListener.onClickSection(position)
            }

            itemView.section_title.text = currentSection.name
            itemView.section_item_card.setOnClickListener{

//                cardItem.setCardBackgroundColor(Color.parseColor("#b70505"))
//                cardItem.setBackgroundColor(R.color.mainColor)
//
//                Log.e("clicked",currentSection.name)



                if (themesRV.visibility == View.GONE){
                    themesRV.visibility = View.VISIBLE
//                    cardItem.setCardBackgroundColor(R.color.mainColor)
                    val themes = Theme(1,"Greeting", 120)
                    val themes1 = Theme(2,"Items", 100)
                    val themes2 = Theme(3,"One My Day", 145)
                    val themes3 = Theme(4,"My Profession", 112)

                    val themesList = arrayListOf(themes,themes1, themes2, themes3)

                    themesRV?.adapter = themesAdapter
                    themesAdapter.submitList(themesList)
//                    cardItem.setBackgroundColor(R.color.black)



                    Log.e("Click", "mainColor - gone")
                }
                else {
                    themesRV.visibility = View.GONE
//                    cardItem.setBackgroundColor(R.color.white)
                    Log.e("Click", "white - visible")



                }

            }

//            itemView.section_image.r = currentSection.image
        }
    }

    interface SectionClickListener{
        fun onClickSection(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        context = parent.context;
        return SectionViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_sections, parent, false)
       )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onClickTheme(position: Int) {
        val themesFragment: Fragment =  ThemesFragment()
        val fragmentManager: FragmentManager? = fragment.activity?.supportFragmentManager


        fragmentManager?.beginTransaction()
            ?.add(android.R.id.content, themesFragment)
            ?.addToBackStack(null)
            ?.commit()

    }
}