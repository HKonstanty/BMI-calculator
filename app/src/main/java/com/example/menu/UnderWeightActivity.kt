package com.example.menu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.TextView

class UnderWeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_under_weight)
        val bmiVal = intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)
        val bmiTV = findViewById<TextView>(R.id.bmiUnderTV)
        bmiTV.text = bmiVal
    }
}