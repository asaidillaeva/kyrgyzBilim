package com.kyrgyzbilim.ui.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.sections.Section
import com.kyrgyzbilim.data.themes.Topic
import com.kyrgyzbilim.ui.courses.sections.SectionsFragment
import com.kyrgyzbilim.ui.courses.sections.themes.ThemesFragment
import kotlinx.android.synthetic.main.item_sections.view.*

class SectionAdapter(
    private val onClickListener: SectionClickListener,
    private val themesOnClickListener: ThemesAdapter.ThemesOnClickListener
): ListAdapter<Section, SectionAdapter.SectionViewHolder>(DIFF){

    private lateinit var context: Context
    private var themesAdapter = ThemesAdapter(themesOnClickListener)

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

            val themes = Topic(1, "Greeting", "" , "" , 120)
            val themes1 = Topic(2, "Items", "" , "" , 100)
            val themes2 = Topic(3, "One My Day", "" , "" , 145)
            val themes3 = Topic(4, "My Profession", "" ,"" ,  112)

            val themesList = arrayListOf(themes, themes1, themes2, themes3)

            themesRV?.adapter = themesAdapter
            themesAdapter.submitList(themesList)

            itemView.section_title.text = currentSection.name
            itemView.section_item_card.setOnClickListener{

//                cardItem.setCardBackgroundColor(Color.parseColor("#b70505"))
//                cardItem.setBackgroundColor(R.color.mainColor)

                if (themesRV.visibility == View.GONE){
                    themesRV.visibility = View.VISIBLE
//                    cardItem.setCardBackgroundColor(R.color.mainColor)
                    animate(themesRV, true, object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            val layoutParams = themesRV.layoutParams
                            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        }
                    })
//                    cardItem.setBackgroundColor(R.color.black)

                    Log.e("Click", "mainColor - gone")
                }
                else {
                    animate(themesRV, false, object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            themesRV.visibility = View.GONE
                        }
                    })
                    // cardItem.setBackgroundColor(R.color.white)
                    Log.e("Click", "white - visible")

                }

            }

//            itemView.section_image.r = currentSection.image
        }
    }

    fun animate(view: View, expand: Boolean = true, animationEndListener: AnimatorListenerAdapter) {
        view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight = view.measuredHeight
        var anim = ValueAnimator.ofInt(view.measuredHeight, targetHeight)
        if (expand){
            view.layoutParams.height = 0
            anim.addUpdateListener { animation ->
                val layoutParams = view.layoutParams
                layoutParams.height = (targetHeight * animation.animatedFraction).toInt()
                view.layoutParams = layoutParams
            }
        }else{
            anim = ValueAnimator.ofInt(view.measuredHeight, 0)
            anim.addUpdateListener { animation ->
                val layoutParams = view.layoutParams
                layoutParams.height = (view.measuredHeight - (view.measuredHeight * animation.animatedFraction)).toInt()
                view.layoutParams = layoutParams
            }
        }
        view.visibility = View.VISIBLE

        anim.interpolator = AccelerateInterpolator()
        anim.duration = 200

        anim.addListener(animationEndListener)
        anim.start()
    }


    interface SectionClickListener{
        fun onClickSection(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        context = parent.context;
        return SectionViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_sections, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.onBind(position)
    }

}