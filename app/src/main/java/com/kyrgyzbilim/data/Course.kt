package com.kyrgyzbilim.data

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