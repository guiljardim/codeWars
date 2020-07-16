package com.example.codewars.util

class ViewData<D>(val status: Status, var data: D? = null, val error: Throwable? = null) {
    enum class Status {
        LOADING, SUCCESS, COMPLETE, ERROR
    }
}