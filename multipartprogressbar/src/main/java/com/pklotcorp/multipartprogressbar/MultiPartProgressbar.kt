package com.pklotcorp.multipartprogressbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.util.SizeF
import android.view.View
import com.pklotcorp.multipartprogressbar.painter.Painter
import com.pklotcorp.multipartprogressbar.painter.ProgressIconPainter
import com.pklotcorp.multipartprogressbar.painter.ProgressPartPainter
import com.pklotcorp.multipartprogressbar.part.ProgressPart
import com.pklotcorp.multipartprogressbar.part.ProgressPartConfig
import com.pklotcorp.multipartprogressbar.util.dp
import kotlin.math.min

open class MultiPartProgressbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), MultiPartProgressbarDelegate {

    private var radius = 0f
    private val size = RectF()
    private val iconResource: Int
    private val iconRadius: Float
    private val progressWidth: Float
    private val painters: MutableList<Painter> = mutableListOf()
    private var progressPartConfig: ProgressPartConfig? = null

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.MultiProgressbarView, 0, 0)
        typedArray.run {
            try {
                iconResource = getResourceId(R.styleable.MultiProgressbarView_icon_resource, 0)
                iconRadius = getDimension(R.styleable.MultiProgressbarView_icon_radius, 5.dp.toFloat())
                progressWidth = getDimension(R.styleable.MultiProgressbarView_progress_width, 10.dp.toFloat())
            } finally {
                recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val edgeLength = min(width, height)
        setMeasuredDimension(edgeLength, edgeLength)
        size.set(0f, 0f, edgeLength.toFloat(), edgeLength.toFloat())
        radius = edgeLength / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        painters.forEach { painter -> painter.draw(canvas) }
    }

    fun setupProgressParts(progressParts: List<ProgressPart>) {
        progressPartConfig = ProgressPartConfig.create(progressParts)
        painters.clear()
        painters.addAll(providePainters())
    }

    fun setProgress(percent: Float) {
        painters.forEach { painter -> painter.percent = percent }
        invalidate()
    }

    open fun providePainters(): List<Painter> {
        return listOf(
            ProgressPartPainter(this),
            ProgressIconPainter(context, this),
        )
    }

    override fun getViewSize(): SizeF {
        return SizeF(size.width(), size.height())
    }

    override fun getViewCenterPoint(): PointF {
        return PointF(size.centerX(), size.centerY())
    }

    override fun getViewRadius(): Float {
        return radius
    }

    override fun getIconResource(): Int {
        return iconResource
    }

    override fun getIconRadius(): Float {
        return iconRadius
    }

    override fun getProgressWidth(): Float {
        return progressWidth
    }

    override fun getColorProgressConfig(): ProgressPartConfig {
        return checkNotNull(progressPartConfig) { "ColorProgressConfig is null, please setup ColorProgressConfig first" }
    }
}