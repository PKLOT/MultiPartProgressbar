package com.carterchen247.multiplepartprogressbar.painter

import android.graphics.Canvas

abstract class Painter {

    open var percent = 0f

    abstract fun draw(canvas: Canvas)
}