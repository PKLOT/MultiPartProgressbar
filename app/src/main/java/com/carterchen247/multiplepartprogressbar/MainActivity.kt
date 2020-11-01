package com.carterchen247.multiplepartprogressbar

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.carterchen247.multiplepartprogressbar.part.ProgressPart
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressbar.setupProgressParts(
            listOf(
                object : ProgressPart() {
                    override fun startColor() = Color.parseColor("#45b035")
                    override fun endColor() = Color.parseColor("#007f41")
                    override fun minValue() = 0
                    override fun maxValue() = 20000
                },
                object : ProgressPart() {
                    override fun startColor() = Color.parseColor("#f6ab00")
                    override fun endColor() = Color.parseColor("#ed6c00")
                    override fun minValue() = 20000
                    override fun maxValue() = 40000
                },
                object : ProgressPart() {
                    override fun startColor() = Color.parseColor("#fff78c")
                    override fun endColor() = Color.parseColor("#fdd000")
                    override fun minValue() = 40000
                    override fun maxValue() = 60000
                },
                object : ProgressPart() {
                    override fun startColor() = Color.parseColor("#37bef0")
                    override fun endColor() = Color.parseColor("#006fbc")
                    override fun minValue() = 60000
                    override fun maxValue() = 80000
                },
                object : ProgressPart() {
                    override fun startColor() = Color.parseColor("#e60012")
                    override fun endColor() = Color.parseColor("#a72126")
                    override fun minValue() = 80000
                    override fun maxValue() = 99999
                },
            )
        )
        playProgressbarAnimation()
    }

    private fun playProgressbarAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 1f).apply {
            interpolator = DecelerateInterpolator()
            duration = 5000L
            addUpdateListener { animation ->
                val percent = animation.animatedValue as Float
                progressbar.setProgress(percent)
            }
        }
        animator.start()
    }
}