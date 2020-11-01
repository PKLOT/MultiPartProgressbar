package com.carterchen247.multiplepartprogressbar.painter

import android.graphics.*
import com.carterchen247.multiplepartprogressbar.MultiplePartProgressbarDelegate
import com.carterchen247.multiplepartprogressbar.part.ProgressPartColor
import com.carterchen247.multiplepartprogressbar.util.ClockWiseAngle
import kotlin.math.atan2
import kotlin.math.max
import kotlin.math.min

class ProgressPartPainter(private val delegate: MultiplePartProgressbarDelegate) : Painter() {

    private val progressColors by lazy {
        val colorProgressConfig = delegate.getColorProgressConfig()
        val colors = colorProgressConfig.progressPartColors
        colors.reversed() // draw first color on the top of canvas
    }
    private val paints by lazy { createPaints() }
    private val arcDrawingArea by lazy { createArcDrawingArea() }
    private val arcFineTuneAngle by lazy { createFineTuneAngle() }

    override fun draw(canvas: Canvas) {
        val currentPercent = percent
        progressColors.forEachIndexed { index, progressColor ->
            val percentDiff = progressColor.endPercent - progressColor.startPercent
            val offsetAngle = 360f * progressColor.startPercent
            val sweepAngle = 360f * percentDiff
            canvas.drawArc(
                arcDrawingArea,
                ClockWiseAngle.toArcDrawingDegree(offsetAngle),
                sweepAngle * ((min(
                    max(currentPercent - progressColor.startPercent, 0f),
                    percentDiff
                ) / percentDiff)),
                false,
                paints[index]
            )
        }
    }

    private fun createPaints(): List<Paint> {
        return progressColors.map { progressColor ->
            Paint().apply {
                isAntiAlias = true
                shader = createSweepGradient(progressColor)
                style = Paint.Style.STROKE
                strokeWidth = delegate.getProgressWidth()
                strokeCap = Paint.Cap.ROUND
            }
        }
    }

    private fun createSweepGradient(progressPartColor: ProgressPartColor): SweepGradient {
        val viewCenterPoint = delegate.getViewCenterPoint()
        val sweepAngle = 360f * progressPartColor.getPercentage()
        val fineTunedAngle = if (sweepAngle - arcFineTuneAngle > 0f) sweepAngle - arcFineTuneAngle else sweepAngle
        return SweepGradient(
            viewCenterPoint.x,
            viewCenterPoint.y,
            intArrayOf(
                progressPartColor.startColor,
                progressPartColor.endColor
            ),
            floatArrayOf(
                ClockWiseAngle.toArcDrawingPosition(0f),
                ClockWiseAngle.toArcDrawingPosition(fineTunedAngle)
            )
        ).apply {
            val rotatedMatrix = Matrix().apply {
                setRotate(
                    ClockWiseAngle.toArcDrawingDegree(360f * progressPartColor.startPercent) - arcFineTuneAngle,
                    viewCenterPoint.x,
                    viewCenterPoint.y
                )
            }
            setLocalMatrix(rotatedMatrix)
        }
    }

    private fun createArcDrawingArea(): RectF {
        return delegate.getViewSize().let { viewSize ->
            val iconRadius = delegate.getIconRadius()
            RectF(
                iconRadius,
                iconRadius,
                viewSize.width - iconRadius,
                viewSize.height - iconRadius
            )
        }
    }

    /**
     * avoid to show the connecting part of arc at 3 o'clock position
     * see [Canvas.drawArc] and [SweepGradient]
     */
    private fun createFineTuneAngle(): Float {
        val factor = 0.75f
        val radian = atan2(delegate.getProgressWidth() * factor, delegate.getViewRadius())
        return Math.toDegrees(radian.toDouble()).toFloat()
    }
}