package com.mikeshong.mii.customclock

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class SmallClock(context: Context, attrs: AttributeSet) : View(context, attrs)
{



    private var firstHandDegree: Float = 0F
    private var secondHandDegree: Float = 60F


    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SmallClock,
            0, 0).apply {

            try {
                firstHandDegree = getFloat(R.styleable.SmallClock_time, 0.toFloat())

                Log.d("Custom", "good, we got degree = $firstHandDegree")
            } finally {
                recycle()
            }
        }
    }

    fun setTime(newTime: Float) {
        animateToTime(newTime)
    }

    fun setFirstHandDegree( degree: Float) {
        animateToTime(degree)
    }

    fun setSecondHandDegree( degree: Float) {
        secondHandDegree = degree
    }

    fun animateToTime(newTime: Float) {
        Log.d("Custom", "About to start the animation")
        ValueAnimator.ofFloat(firstHandDegree, newTime).apply {
            duration = 6000

            addUpdateListener { updatedAnimation ->

                firstHandDegree = updatedAnimation.animatedValue as Float
                invalidate()
            }

            start()
        }
    }


    override fun onDraw(canvas: Canvas) {

        canvas.drawColor(Color.rgb(255, 255, 255))

        val redPaint = Paint().apply {
            setColor(Color.rgb(20, 25,25))
            strokeWidth = 10F
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }


        val width = canvas.width
        val height = canvas.height

        val dimension: Int

        if (width> height) dimension = height
        else dimension = width



        /// Draw First hand

        val startX = width / 2
        val startY = height / 2

        val length = dimension / 2 - 20

        Log.d("Custom", "the lenght = ${length}")

        var endX = startX + Math.sin(Math.toRadians(firstHandDegree.toDouble())) * length
        var endY = startY + (-1) * Math.cos(Math.toRadians(firstHandDegree.toDouble())) *length



        Log.d("Custom", "Length=${length}, endX=${endX}, endY=${endY}")

        canvas.drawLine( startX.toFloat(), startY.toFloat(), endX.toFloat(), endY.toFloat(), redPaint)


        /// Draw Second Hand

        endX = startX + Math.sin(Math.toRadians(secondHandDegree.toDouble())) * length
        endY = startY + (-1) * Math.cos(Math.toRadians(secondHandDegree.toDouble())) *length

        canvas.drawLine( startX.toFloat(), startY.toFloat(), endX.toFloat(), endY.toFloat(), redPaint)


    }

}
