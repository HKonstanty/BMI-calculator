package com.example.menu

import android.graphics.Color
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class OptBmiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opt_bmi)
        val bmiVal = intent.getStringExtra(EXTRA_MESSAGE)
        val bmiTV = findViewById<TextView>(R.id.bmiOpTV)
        bmiTV.text = bmiVal
    }
}