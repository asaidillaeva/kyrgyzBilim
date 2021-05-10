package com.kyrgyzbilim.data.course

class Course (
    val id: Int,
    val name: String,
    val description: String,
    val progress : Int

) {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + progress
        return result
    }
}