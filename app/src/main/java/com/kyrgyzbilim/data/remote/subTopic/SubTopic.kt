package com.kyrgyzbilim.data.remote.subTopic

data class SubTopic(
    val id: Int = 0,
    val text: String = "",
    val translated_text: String = "",
    val audio: String = "",
    val image: String = " ",
    val type: String = ""
) {

    override fun toString(): String {
        return "SubTopic(id=$id, name='$text', translated_name='$translated_text', icon='$image', audio='$audio', type='$type')"
    }
}