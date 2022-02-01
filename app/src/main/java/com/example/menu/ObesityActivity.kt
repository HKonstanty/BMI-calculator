package com.example.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.TextView

class ObesityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obesity)
        val bmiVal = intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)
        val bmiTV = findViewById<TextView>(R.id.bmiObesityTV)
        bmiTV.text = bmiVal
    }
}