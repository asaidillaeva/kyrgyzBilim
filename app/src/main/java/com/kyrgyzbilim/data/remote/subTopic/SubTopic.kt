package com.kyrgyzbilim.data.remote.subTopic

data class SubTopic(
    val id: Int = 0,
    val name: String = "",
    val translated_name: String = "",
    val audio: String? = "",
    val image: String = " ",
    val type: String = ""
) {

    override fun toString(): String {
        return "SubTopic(id=$id, icon='$image', audio='$audio', type='$type')"
    }
}