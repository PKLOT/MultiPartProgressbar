package com.carterchen247.multiplepartprogressbar.util

import com.carterchen247.multiplepartprogressbar.part.ProgressPartConfig
import com.google.android.material.animation.ArgbEvaluatorCompat

class ProgressColorProvider(private val config: ProgressPartConfig) {

    var currentProgress: Float = 0f

    fun getColor(): Int {
        val currentProgressPart = config.progressPartColors.findLast {
            it.startPercent <= currentProgress && currentProgress <= it.endPercent
        }
        checkNotNull(currentProgressPart, { "ProgressPartConfig setting error" })
        return ArgbEvaluatorCompat.getInstance()
            .evaluate(
                (currentProgress - currentProgressPart.startPercent) / (currentProgressPart.endPercent - currentProgressPart.startPercent),
                currentProgressPart.startColor,
                currentProgressPart.endColor
            )
    }
}