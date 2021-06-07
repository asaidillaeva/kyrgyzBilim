package com.kyrgyzbilim.data.local

class Progress {
    val map = hashMapOf<String, SectionProgress>()
    class SectionProgress {
        var sectionItemsCount: Int = 0
        var sectionCompleteCount: Int = 0
    }
}
