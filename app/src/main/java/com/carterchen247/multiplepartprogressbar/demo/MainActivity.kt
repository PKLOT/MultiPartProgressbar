package com.carterchen247.multiplepartprogressbar.demo

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.carterchen247.multiplepartprogressbar.part.ProgressPart
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var animator: Animator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playButton.setOnClickListener {
            playProgressbarAnimation()
        }

        defaultProgressbar.setupProgressParts(getDefaultProgressbarParts())
        customizedProgressbar.setupProgressParts(getCustomizedProgressbarParts())
        playProgressbarAnimation()
    }

    private fun playProgressbarAnimation() {
        animator?.cancel()
        animator = ValueAnimator.ofFloat(0f, 1f).apply {
            interpolator = DecelerateInterpolator()
            duration = 5000L
            addUpdateListener { animation ->
                val percent = animation.animatedValue as Float
                defaultProgressbar.setProgress(percent)
                customizedProgressbar.setProgress(percent * 0.9f)
            }
        }
        animator?.start()
    }

    private fun getDefaultProgressbarParts(): List<ProgressPart> {
        return listOf(
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
    }

    private fun getCustomizedProgressbarParts(): List<ProgressPart> {
        return listOf(
            object : ProgressPart() {
                override fun startColor() = Color.MAGENTA
                override fun endColor() = Color.RED
                override fun minValue() = 0
                override fun maxValue() = 30
            },
            object : ProgressPart() {
                override fun startColor() = Color.CYAN
                override fun endColor() = Color.BLUE
                override fun minValue() = 30
                override fun maxValue() = 70
            },
            object : ProgressPart() {
                override fun startColor() = Color.YELLOW
                override fun endColor() = Color.GREEN
                override fun minValue() = 70
                override fun maxValue() = 120
            },
        )
    }
}