package com.example.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val itemsList: ArrayList<Measurement>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        while (itemsList.size > 10)
            itemsList.removeAt(itemsList.size - 1)
        return itemsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.bmi_row, parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = itemsList[position]
        holder.height.text = currentItem.height
        holder.mass.text = currentItem.mass
        holder.bmi.text = currentItem.bmi
        holder.date.text = currentItem.date
        holder.bmi.setTextColor(currentItem.bmiColor)
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val height: TextView = view.findViewById(R.id.heightTV)
    val mass: TextView = view.findViewById(R.id.massTV)
    val bmi: TextView = view.findViewById(R.id.bmiTV)
    val date: TextView = view.findViewById(R.id.dateTV)
}