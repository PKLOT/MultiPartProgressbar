package com.carterchen247.multiplepartprogressbar

import com.carterchen247.multiplepartprogressbar.util.ClockWiseAngle
import org.junit.Assert.assertEquals
import org.junit.Test

class ClockWiseAngleTest {
    @Test
    fun `expect 90 degree when sweep to 0 o'clock`() {
        assertEquals(90f, ClockWiseAngle.toMathDegree(0f))
    }

    @Test
    fun `expect 0 degree when sweep to 3 o'clock`() {
        assertEquals(0f, ClockWiseAngle.toMathDegree(90f))
    }

    @Test
    fun `expect 270 degree when sweep to 6 o'clock`() {
        assertEquals(270f, ClockWiseAngle.toMathDegree(180f))
    }

    @Test
    fun `expect 180 degree when sweep to 9 o'clock`() {
        assertEquals(180f, ClockWiseAngle.toMathDegree(270f))
    }

    @Test
    fun `expect 90 degree when sweep to 12 o'clock`() {
        assertEquals(90f, ClockWiseAngle.toMathDegree(360f))
    }

    @Test
    fun `expect arc drawing position is at 0 when sweep to 0 o'clock`() {
        assertEquals(0f, ClockWiseAngle.toArcDrawingPosition(0f))
    }

    @Test
    fun `expect arc drawing position is at 0_25 when sweep to 3 o'clock`() {
        assertEquals(0.25f, ClockWiseAngle.toArcDrawingPosition(90f))
    }

    @Test
    fun `expect arc drawing position is at 0_5 when sweep to 6 o'clock`() {
        assertEquals(0.5f, ClockWiseAngle.toArcDrawingPosition(180f))
    }

    @Test
    fun `expect arc drawing position is at 0_75 when sweep to 9 o'clock`() {
        assertEquals(0.75f, ClockWiseAngle.toArcDrawingPosition(270f))
    }

    @Test
    fun `expect arc drawing position is at 1 when sweep to 12 o'clock`() {
        assertEquals(1f, ClockWiseAngle.toArcDrawingPosition(360f))
    }

    @Test
    fun `expect arc drawing matrix rotation is 270 degree when sweep to 0 o'clock`() {
        assertEquals(270f, ClockWiseAngle.toArcDrawingDegree(0f))
    }

    @Test
    fun `expect arc drawing matrix rotation is 270 degree when sweep to 3 o'clock`() {
        assertEquals(0f, ClockWiseAngle.toArcDrawingDegree(90f))
    }

    @Test
    fun `expect arc drawing matrix rotation is 270 degree when sweep to 6 o'clock`() {
        assertEquals(90f, ClockWiseAngle.toArcDrawingDegree(180f))
    }

    @Test
    fun `expect arc drawing matrix rotation is 270 degree when sweep to 9 o'clock`() {
        assertEquals(180f, ClockWiseAngle.toArcDrawingDegree(270f))
    }

    @Test
    fun `expect arc drawing matrix rotation is 270 degree when sweep to 12 o'clock`() {
        assertEquals(270f, ClockWiseAngle.toArcDrawingDegree(360f))
    }
}