package com.pklotcorp.multipartprogressbar.part

data class ProgressPartColor(
    val startPercent: Float,
    val endPercent: Float,
    val startColor: Int,
    val endColor: Int
) {
    fun getPercentage() = endPercent - startPercent
}