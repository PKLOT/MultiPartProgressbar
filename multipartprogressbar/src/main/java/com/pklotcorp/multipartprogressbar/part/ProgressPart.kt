package com.pklotcorp.multipartprogressbar.part

abstract class ProgressPart {
    abstract fun startColor(): Int
    abstract fun endColor(): Int
    abstract fun minValue(): Int
    abstract fun maxValue(): Int
    fun getPartValue() = maxValue() - minValue()
}