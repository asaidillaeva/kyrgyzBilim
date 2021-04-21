package com.kyrgyzbilim.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.course.Course
import kotlinx.android.synthetic.main.item_courses.view.*

class CoursesAdapter(
        private val onClickListener: CoursesClickListener
) : ListAdapter<Course, CoursesAdapter.CourseViewHolder>(DIFF) {


    companion object{
        private  val DIFF = object : DiffUtil.ItemCallback<Course>(){
            override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            val currentCourse = getItem(position)

            itemView.level_title.text = currentCourse.name
            itemView.course_description.text = currentCourse.description
            itemView.percent_progress.text = currentCourse.progress.toString() + "%"

            itemView.setOnClickListener {
                onClickListener.onClickCourse(position)
            }
        }
    }

    interface CoursesClickListener{
        fun onClickCourse(position : Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_courses, parent, false))
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.onBind(position)
    }


}



