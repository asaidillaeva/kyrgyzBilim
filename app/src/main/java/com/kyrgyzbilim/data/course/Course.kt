package com.kyrgyzbilim.data.course

class Course(
        val id: Int,
        val levelTitle: String,
        val description: String,
        val progress : Int

        ) {

        override fun equals(other: Any?): Boolean {
                return super.equals(other)
        }
}