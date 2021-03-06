package com.pklotcorp.multipartprogressbar.demo

import android.content.Context
import android.util.AttributeSet
import com.pklotcorp.multipartprogressbar.MultiPartProgressbar
import com.pklotcorp.multipartprogressbar.painter.Painter
import com.pklotcorp.multipartprogressbar.painter.ProgressIconPainter
import com.pklotcorp.multipartprogressbar.painter.ProgressPartPainter

class MyMultiPartProgressbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MultiPartProgressbar(context, attrs, defStyleAttr) {

    override fun providePainters(): List<Painter> {
        return listOf(
            ProgressBackgroundPainter(this, resources.getDimension(R.dimen.width_background_progress)),
            ProgressPartPainter(this),
            ProgressIconPainter(context, this),
        )
    }
}