package com.example.menu

import android.os.Bundle
import android.provider.AlarmClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OverWeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_over_weight)
        val bmiVal = intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)
        val bmiTV = findViewById<TextView>(R.id.bmiOverTV)
        bmiTV.text = bmiVal
    }
}