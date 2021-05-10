package com.kyrgyzbilim.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.OnItemClickListener
import com.kyrgyzbilim.data.course.Course
import kotlinx.android.synthetic.main.item_courses.view.*

class CourseAdapter(
    private val items: List<Course>?,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CourseAdapter.CharactersViewHolder>() {



    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(
            course: Course,
            onItemClickListener: OnItemClickListener
        ) {

            itemView.level_title.text = course.name
            itemView.course_description.text = course.description
            itemView.percent_progress.text = course.progress.toString()

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(course)

            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_courses, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.onBind(items!![position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return items?.size!!

    }


}
