package com.kyrgyzbilim.ui.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.remote.sections.Section
import com.kyrgyzbilim.data.remote.topic.Topic
import com.kyrgyzbilim.ui.courses.sections.SectionViewModel
import com.kyrgyzbilim.ui.courses.sections.SectionsFragment
import kotlinx.android.synthetic.main.item_sections.view.*

class SectionAdapter(
    private val onClickListener: SectionClickListener,
    private val themesOnClickListener: ThemesAdapter.ThemesOnClickListener,
    private var sectionList: List<Section>?
) : ListAdapter<Section, SectionAdapter.SectionViewHolder>(DIFF) {

    private lateinit var context: Context
    private var themesAdapter = ThemesAdapter()

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Section>() {
            override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor")
        fun onBind(position: Int) {
            val currentSection = getItem(position)

            val themesRV = itemView.themes_RV
            val cardItem = itemView.section_background

            itemView.setOnClickListener {
                onClickListener.onClickSection(position)
            }


            themesAdapter.setData(themesOnClickListener, sectionList?.get(position)?.topics)
            themesRV?.adapter = themesAdapter
            themesAdapter.submitList(sectionList?.get(position)?.topics)

            itemView.section_title.text = currentSection.name

            itemView.section_item_card.setOnClickListener {

                if (themesRV.visibility == View.GONE) {
                    themesRV.visibility = View.VISIBLE

                    animate(themesRV, true, object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            val layoutParams = themesRV.layoutParams
                            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

                        }

                    })


                } else {
                    animate(themesRV, false, object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            themesRV.visibility = View.GONE
                        }
                    })

                }

            }

        }
    }

    fun animate(view: View, expand: Boolean = true, animationEndListener: AnimatorListenerAdapter) {
        view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight = view.measuredHeight
        var anim = ValueAnimator.ofInt(view.measuredHeight, targetHeight)
        if (expand) {
            view.layoutParams.height = 0
            anim.addUpdateListener { animation ->
                val layoutParams = view.layoutParams
                layoutParams.height = (targetHeight * animation.animatedFraction).toInt()
                view.layoutParams = layoutParams
            }
        } else {
            anim = ValueAnimator.ofInt(view.measuredHeight, 0)
            anim.addUpdateListener { animation ->
                val layoutParams = view.layoutParams
                layoutParams.height =
                    (view.measuredHeight - (view.measuredHeight * animation.animatedFraction)).toInt()
                view.layoutParams = layoutParams
            }
        }
        view.visibility = View.VISIBLE

        anim.interpolator = AccelerateInterpolator()
        anim.duration = 200

        anim.addListener(animationEndListener)
        anim.start()
    }


    interface SectionClickListener {
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

    fun getItemAtPos(position: Int): Section {
        return getItem(position)
    }

}