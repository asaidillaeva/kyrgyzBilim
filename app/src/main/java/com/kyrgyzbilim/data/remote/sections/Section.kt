package com.kyrgyzbilim.data.remote.sections

import com.kyrgyzbilim.data.remote.topic.Topic

class Section(
    val id: Int,
    val name: String,
//    val icon: Int,
    val topics: List<Topic>,
    val type: String
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun toString(): String {
        return "Section(id=$id, name='$name', topics=$topics)"
    }


}