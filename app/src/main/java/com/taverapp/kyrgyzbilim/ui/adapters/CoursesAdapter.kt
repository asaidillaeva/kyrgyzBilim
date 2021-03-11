package com.taverapp.kyrgyzbilim.ui.adapters

import android.view.View
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.taverapp.kyrgyzbilim.data.Course

class CoursesAdapter(
        private val onClick: CoursesClickListener
) : ListAdapter<Course, CoursesAdapter.CourseViewHolder(DIFF) {

    companion object{
        private  val DIFF = object : DiffUtil.ItemCallback<Course>(){
            override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem.courseName == newItem.courseName
            }

            override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem.equals(newItem)
//                return oldItem == newItem
            }

        }
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface CoursesClickListener{
        fun onClickCourse(position : Int)
    }


}

