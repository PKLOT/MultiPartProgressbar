package com.pklotcorp.multipartprogressbar.painter

import android.graphics.Canvas

abstract class Painter {

    open var percent = 0f

    abstract fun draw(canvas: Canvas)
}