package com.example.codewars.util

import com.example.codewars.data.model.Overall

fun Map<String, Overall>.getBetterLanguage(): String? {
    val mapCompare = mutableMapOf<String, Int>()

    this.forEach {
        mapCompare[it.key] = it.value.score.toInt()
    }
    return mapCompare.maxBy { it.value }?.key
}

fun String.formatToExhibition(title: String): String? = "$title : $this"
