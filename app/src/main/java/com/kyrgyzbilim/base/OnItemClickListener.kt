package com.kyrgyzbilim.base

//interface OnItemClickListener {
//    fun onItemClick(note: Note?)
//}

interface OnItemClickListener {
    fun <T> onItemClick(listItem: T)
}