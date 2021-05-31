package com.kyrgyzbilim.data.remote.subTopic

data class SubTopic(
    val id: Int = 0,
    val body: String = "",
    val name: String = "",
    val translated_name: String = "",
    val icon: String = " ",
    val audio: String = "",
    val type: String = ""
) {

    override fun toString(): String {
        return "SubTopic(id=$id, name='$name', translated_name='$translated_name', icon='$icon', audio='$audio', type='$type')"
    }
}