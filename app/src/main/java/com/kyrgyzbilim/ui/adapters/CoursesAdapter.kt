package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.data.remote.course.Course
import com.kyrgyzbilim.ui.courses.CoursesFragmentDirections
import kotlinx.android.synthetic.main.item_courses.view.*

class CoursesAdapter : ListAdapter<Course, CoursesAdapter.CourseViewHolder>(DIFF) {

    private lateinit var context: Context

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Course>() {
            override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor")
        fun onBind(position: Int) {
            val course = getItem(position)

            itemView.level_title.text = course.name
            itemView.course_description.text = course.description
            itemView.percent_progress.text = course.progress.toString()
            itemView.level_progress_bar.setProgressCompat(course.progress, true)

            itemView.setOnClickListener {
                val action = CoursesFragmentDirections.actionCoursesFragmentToSectionsFragment(course.id)
                val nav = Navigation.findNavController(it)
                nav.navigateUp()
                nav.navigate(action)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        context = parent.context;
        return CourseViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_courses, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.onBind(position)
    }


    fun getItemAtPos(position: Int): Course {
        return getItem(position)
    }
}
