package com.kyrgyzbilim.data.remote.subTopic

data class SubTopic(
    val id: Int = 0,
    val text: String = "",
    val translated_text: String = "",
    val audio: String? = "",
    val image: String = " ",
    val type: String = "",
    val order: Int
)
