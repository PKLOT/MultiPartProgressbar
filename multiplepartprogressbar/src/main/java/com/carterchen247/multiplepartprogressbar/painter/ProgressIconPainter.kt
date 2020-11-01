package com.carterchen247.multiplepartprogressbar.painter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.carterchen247.multiplepartprogressbar.MultipleProgressbarViewDelegate
import com.carterchen247.multiplepartprogressbar.util.ClockWiseAngle
import com.carterchen247.multiplepartprogressbar.util.ProgressColorProvider
import kotlin.math.cos
import kotlin.math.sin

class ProgressIconPainter(
    private val context: Context,
    private val delegate: MultipleProgressbarViewDelegate
) : Painter() {

    private val iconBitmap by lazy {
        AppCompatResources.getDrawable(
            context,
            delegate.getIconResource()
        )?.toBitmap()
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