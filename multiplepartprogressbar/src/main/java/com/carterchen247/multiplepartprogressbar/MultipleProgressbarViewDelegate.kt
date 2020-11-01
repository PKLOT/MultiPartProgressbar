package com.carterchen247.multiplepartprogressbar

import android.graphics.PointF
import android.util.SizeF
import com.carterchen247.multiplepartprogressbar.part.ProgressPartConfig

interface MultipleProgressbarViewDelegate {
    fun getViewSize(): SizeF
    fun getViewCenterPoint(): PointF
    fun getViewRadius(): Float
    fun getIconResource(): Int
    fun getIconRadius(): Float
    fun getProgressWidth(): Float
    fun getColorProgressConfig(): ProgressPartConfig
}