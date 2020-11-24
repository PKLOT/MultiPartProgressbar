package com.pklotcorp.multipartprogressbar

import android.graphics.PointF
import android.util.SizeF
import com.pklotcorp.multipartprogressbar.part.ProgressPartConfig

interface MultiplePartProgressbarDelegate {
    fun getViewSize(): SizeF
    fun getViewCenterPoint(): PointF
    fun getViewRadius(): Float
    fun getIconResource(): Int
    fun getIconRadius(): Float
    fun getProgressWidth(): Float
    fun getColorProgressConfig(): ProgressPartConfig
}