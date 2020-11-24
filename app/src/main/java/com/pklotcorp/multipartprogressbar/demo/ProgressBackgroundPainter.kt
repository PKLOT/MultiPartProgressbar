package com.pklotcorp.multipartprogressbar.demo

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.pklotcorp.multipartprogressbar.MultiplePartProgressbarDelegate
import com.pklotcorp.multipartprogressbar.painter.Painter

class ProgressBackgroundPainter(
    private val delegate: MultiplePartProgressbarDelegate,
    private val backgroundProgressWidth: Float
) : Painter() {

    private val backgroundPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = Color.parseColor("#EEEEEE")
            strokeWidth = backgroundProgressWidth
            style = Paint.Style.STROKE
        }
    }

    private val arcDrawingArea by lazy {
        delegate.getViewSize().let { viewSize ->
            val iconRadius = delegate.getIconRadius()
            RectF(
                iconRadius,
                iconRadius,
                viewSize.width - iconRadius,
                viewSize.height - iconRadius
            )
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawArc(arcDrawingArea, 0f, 360f, false, backgroundPaint)
    }
}