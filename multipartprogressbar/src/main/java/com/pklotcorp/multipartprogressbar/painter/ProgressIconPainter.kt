package com.pklotcorp.multipartprogressbar.painter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.pklotcorp.multipartprogressbar.MultiPartProgressbarDelegate
import com.pklotcorp.multipartprogressbar.util.ClockWiseAngle
import com.pklotcorp.multipartprogressbar.util.ProgressColorProvider
import kotlin.math.cos
import kotlin.math.sin

class ProgressIconPainter(
    private val context: Context,
    private val delegate: MultiPartProgressbarDelegate
) : Painter() {

    private val iconBitmap by lazy {
        delegate.getIconResource().let { resourceId ->
            if (resourceId == 0) return@lazy null
            AppCompatResources.getDrawable(context, delegate.getIconResource())?.toBitmap()
        }
    }
    private val iconBackgroundPaint by lazy { Paint().apply { isAntiAlias = true } }
    private val progressColorProvider by lazy { ProgressColorProvider(delegate.getColorProgressConfig()) }

    override var percent: Float
        get() = super.percent
        set(value) {
            super.percent = value
            progressColorProvider.currentProgress = percent
        }

    override fun draw(canvas: Canvas) {
        val viewCenterPoint = delegate.getViewCenterPoint()
        val sweepAngle = ClockWiseAngle.toMathDegree(360f * percent)
        val targetCenterPoint = getDistanceDiffByAngle(sweepAngle, delegate.getViewRadius() - delegate.getIconRadius())

        canvas.drawCircle(
            viewCenterPoint.x + targetCenterPoint.x,
            viewCenterPoint.y + targetCenterPoint.y,
            delegate.getIconRadius(),
            iconBackgroundPaint.apply { color = progressColorProvider.getColor() }
        )

        iconBitmap?.let { bitmap ->
            canvas.drawBitmap(
                bitmap,
                viewCenterPoint.x + targetCenterPoint.x - delegate.getIconRadius(),
                viewCenterPoint.y + targetCenterPoint.y - delegate.getIconRadius(),
                null
            )
        }
    }

    private fun getDistanceDiffByAngle(angle: Float, radius: Float): PointF {
        val radians = Math.toRadians(angle.toDouble())
        return PointF(
            (cos(radians) * 1).toFloat() * radius,
            (sin(radians) * -1).toFloat() * radius
        )
    }
}