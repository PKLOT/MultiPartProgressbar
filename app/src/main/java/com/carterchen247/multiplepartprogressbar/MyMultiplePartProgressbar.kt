package com.carterchen247.multiplepartprogressbar

import android.content.Context
import android.util.AttributeSet
import com.carterchen247.multiplepartprogressbar.painter.Painter
import com.carterchen247.multiplepartprogressbar.painter.ProgressIconPainter
import com.carterchen247.multiplepartprogressbar.painter.ProgressPartPainter

class MyMultiplePartProgressbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MultiplePartProgressbar(context, attrs, defStyleAttr) {

    override fun providePainters(): List<Painter> {
        return listOf(
            ProgressBackgroundPainter(this, resources.getDimension(R.dimen.width_background_progress)),
            ProgressPartPainter(this),
            ProgressIconPainter(context, this),
        )
    }
}