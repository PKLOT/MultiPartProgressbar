package com.carterchen247.multiplepartprogressbar.demo

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
                override fun startColor() = ContextCompat.getColor(this@MainActivity, R.color.material_pink_200)
                override fun endColor() = ContextCompat.getColor(this@MainActivity, R.color.material_pink_900)
                override fun minValue() = 0
                override fun maxValue() = 20000
            },
            object : ProgressPart() {
                override fun startColor() = ContextCompat.getColor(this@MainActivity, R.color.material_yellow_200)
                override fun endColor() = ContextCompat.getColor(this@MainActivity, R.color.material_yellow_900)
                override fun minValue() = 20000
                override fun maxValue() = 40000
            },
            object : ProgressPart() {
                override fun startColor() = ContextCompat.getColor(this@MainActivity, R.color.material_green_200)
                override fun endColor() = ContextCompat.getColor(this@MainActivity, R.color.material_green_900)
                override fun minValue() = 40000
                override fun maxValue() = 60000
            },
            object : ProgressPart() {
                override fun startColor() = ContextCompat.getColor(this@MainActivity, R.color.material_blue_200)
                override fun endColor() = ContextCompat.getColor(this@MainActivity, R.color.material_blue_900)
                override fun minValue() = 60000
                override fun maxValue() = 80000
            },
            object : ProgressPart() {
                override fun startColor() = ContextCompat.getColor(this@MainActivity, R.color.material_purple_200)
                override fun endColor() = ContextCompat.getColor(this@MainActivity, R.color.material_purple_900)
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