package com.kyrgyzbilim.data.course

class Course(
        val id: Int,
        val name: String,
        val description: String,
        val progress : Int

        ) {

        override fun equals(other: Any?): Boolean {
                return super.equals(other)
        }
}