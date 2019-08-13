package com.mikeshong.mii.customclock

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() , View.OnClickListener{

    lateinit var smallOne:SmallClock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).apply {
            setOnClickListener(this@MainActivity)
        }

        smallOne = findViewById(R.id.small_one)





    }

    private fun startAnimation() {

        smallOne.setFirstHandDegree(360F)
    }

    override fun onClick(p0: View?) {
        startAnimation()
    }
}
