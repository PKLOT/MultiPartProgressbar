package com.carterchen247.multiplepartprogressbar.util

/**
 *       90
 *       |
 * 180 - x - 0
 *       |
 *      270
 */
/**
 *      270
 *       |
 * 180 - x - 0
 *       |
 *      90
 */

object ClockWiseAngle {

    /**
     * input:
     *       0
     *       |
     * 270 - x - 90
     *       |
     *      180
     *
     * output:
     *       90
     *       |
     * 180 - x - 0
     *       |
     *      270
     */
    fun toMathDegree(clockWiseSweepAngle: Float) = (90f + clockWiseSweepAngle * -1 + 360f) % 360f

    /**
     * convert clockwise sweep angle to color positions in [android.graphics.SweepGradient]
     *
     * input:
     *       0 (0/4f && 4/4f)
     *       |
     * 270 - x - 90 (1/4f)
     * (3/4f)|
     *      180 (2/4f)
     *
     * output:
     *      270 (3/4f)
     *       |
     * 180 - x - 0 (0/4f && 4/4f)
     * (2/4f)|
     *       90 (1/4f)
     */
    fun toArcDrawingPosition(clockWiseSweepAngle: Float) = clockWiseSweepAngle / 360f


    /**
     * input:
     *       0
     *       |
     * 270 - x - 90
     *       |
     *      180
     *
     * output:
     *       270
     *       |
     * 180 - x - 0
     *       |
     *       90
     */
    fun toArcDrawingDegree(clockWiseSweepAngle: Float) = (clockWiseSweepAngle + 270f) % 360f
}