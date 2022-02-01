package com.example.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_main)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //var measurementsList: ArrayList<Item> = intent.extras?.getParcelableArrayList<Item>(MainActivity.KEY_PREFERENCES) as ArrayList<Item>
        var db:DataBaseHandler = DataBaseHandler(this)
        var measurementsList = db.readData()
        recyclerView.adapter = MainAdapter(measurementsList)
        recyclerView.setHasFixedSize(true)
    }
}