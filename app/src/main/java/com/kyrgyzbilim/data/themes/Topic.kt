package com.kyrgyzbilim.data.themes

class Topic(
    val id: Int,
    val name: String,
    val translatedName: String = "",
    val icon: String = "",
    val amountOfWords: Int = 0,
    val subTopics: ArrayList<SubTopic> = arrayListOf()
) {

}