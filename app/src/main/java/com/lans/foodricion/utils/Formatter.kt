package com.lans.foodricion.utils

fun formatFloat(value: Float): String {
    return if (value % 1 == 0f) {
        value.toInt().toString()
    } else {
        String.format("%.1f", value)
    }
}