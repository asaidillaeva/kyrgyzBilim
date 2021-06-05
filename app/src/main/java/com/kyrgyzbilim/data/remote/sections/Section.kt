package com.kyrgyzbilim.data.remote.sections

import com.kyrgyzbilim.data.remote.topic.Topic

class Section(
    val id: Int,
    val name: String,
    val icon: String,
    val type: String,
    val topics: List<Topic>,
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun toString(): String {
        return "Section(id=$id, name='$name', icon='$icon', type='$type', topics=$topics)"
    }


}