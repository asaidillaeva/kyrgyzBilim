package com.kyrgyzbilim.data.remote.topic

import com.kyrgyzbilim.data.remote.subTopic.SubTopic

class Topic(
    val id: Int,
    val name: String,
    val translated_name: String = "",
    val icon: String = "",
    val type: String
) {

    override fun toString(): String {
        return "Topic(id=$id, name='$name', translated_name='$translated_name', icon='$icon', type='$type')"
    }
}