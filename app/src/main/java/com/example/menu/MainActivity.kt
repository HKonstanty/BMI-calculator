package com.example.menu


import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.menu.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var Bmi : BMI = BmiForKgCm()
    var section : Int? = 0
    var BMI_VALUE : String? = null
    var mesurementsList: ArrayList<Item> = ArrayList()
    var euUnits = true
    var db: DataBaseHandler? = null

    companion object{
        const val REQUEST_CODE = 1
        const val SHARDE_PREFERENCES = "shared preferences"
        const val KEY_PREFERENCES = "mesurList"
        const val MODE_VALUE = "euUnits"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            bmiTV.setOnClickListener {
                if (bmiTV.text.toString() == "0.00")
                    Toast.makeText(this@MainActivity, "BMI was not calculated", Toast.LENGTH_LONG).show()
                else
                    sendMessage()
            }
        }
        db = DataBaseHandler(this@MainActivity)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.apply {
            savedInstanceState?.run {
                bmiTV.text = savedInstanceState.getString(BMI_VALUE)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(BMI_VALUE, binding.bmiTV.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    fun sendMessage(){
        var intent : Intent? = null
        val message : String
        binding.apply {
            message = bmiTV.text.toString()
        }
        when (section) {
            1 -> {
                intent = Intent(this, ObesityActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivityForResult(intent, REQUEST_CODE)
            }
            2 -> {
                intent = Intent(this, OverWeightActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivityForResult(intent, REQUEST_CODE)
            }
            3 -> {
                intent = Intent(this, OptBmiActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivityForResult(intent, REQUEST_CODE)
            }
            4 -> {
                intent = Intent(this, UnderWeightActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivityForResult(intent, REQUEST_CODE)
            }
            else -> {
                Toast.makeText(this@MainActivity, "You entered the wrong data", Toast.LENGTH_LONG).show()
                binding.apply {
                    bmiTV.setTextColor(Color.parseColor("#FF000000"))
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

   /* fun saveDate() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(SHARDE_PREFERENCES, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(mesurementsList)
        editor.putString(KEY_PREFERENCES, json)
        editor.apply()
    }

    fun loadDate() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(SHARDE_PREFERENCES, MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(KEY_PREFERENCES, null)
        val type = object : TypeToken<ArrayList<Item>>() {}.type
        if (json != null)
            mesurementsList = gson.fromJson(json, type)
    }*/

    @RequiresApi(Build.VERSION_CODES.O)
    fun count(view: View) {
        binding.apply {
            //TODO oprogramowac liczenie bmi i sprawdzanie danych wejsciowych
            if (heightET.text.isBlank() || massET.text.isBlank()) {
                heightET.error = getString(R.string.height_is_empty)
                massET.error = getString(R.string.height_is_empty)
            }else {
                val bmi = Bmi.countBMI(massET.text.toString().toDouble(), heightET.text.toString().toDouble())
                val bmiColor = setColor(bmi)
                bmiTV.text = String.format("%.2f", bmi)
                if (section != 5){
                    var date = checkDate()
                    if (euUnits) {
                       // mesurementsList.add(0, Item(heightET.text.toString()+" cm", massET.text.toString()+" kg", String.format("%.2f", bmi), bmiColor, date))
                        var measure = Measurement(heightET.text.toString()+" cm", massET.text.toString()+" kg", String.format("%.2f", bmi), bmiColor!!, date)
                        //var db = DataBaseHandler(this@MainActivity)
                        db?.insertData(measure)
                    } else {
                       // mesurementsList.add(0, Item(heightET.text.toString()+" in", massET.text.toString()+" lb", String.format("%.2f", bmi), bmiColor, date))
                        var measure = Measurement(heightET.text.toString()+" in", massET.text.toString()+" lb", String.format("%.2f", bmi), bmiColor!!, date)
                        //var db = DataBaseHandler(this@MainActivity)
                        db?.insertData(measure)
                    }
                }
            }
        }
    }

    private fun checkDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val date = current.format(formatter)
        return date
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.EU -> selectedEU()
            R.id.GB -> selectedGB()
            R.id.menu -> menuSelected()
            R.id.clear_history -> clrHistSelected()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun clrHistSelected() {
        db?.deleteData()
        //mesurementsList.clear()
        //saveDate()
    }

    private fun menuSelected() {
        intent = Intent(this, RecyclerViewActivity::class.java)
        //intent.putExtra(KEY_PREFERENCES, mesurementsList)
        startActivityForResult(intent, REQUEST_CODE)
    }

   /* override fun onPause() {
        super.onPause()
        saveDate()
        val sharedPreferences: SharedPreferences = getSharedPreferences(MODE_VALUE, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(MODE_VALUE, euUnits)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        loadDate()
        val sharedPreferences: SharedPreferences = getSharedPreferences(MODE_VALUE, MODE_PRIVATE)
        euUnits = sharedPreferences.getBoolean(MODE_VALUE, true)
        if (euUnits)
            selectedEU()
        else
            selectedGB()
    }*/


    private fun selectedGB() {
        Bmi = BmiForGB()
        euUnits = false
        binding.apply {
            massTV.text = "Mass [lb]"
            heightTV.text = "Height [in]"
            massET.text.clear()
            heightET.text.clear()
            bmiTV.text =  "0.00"
        }
    }

    private fun selectedEU() {
        Bmi = BmiForKgCm()
        euUnits = true
        binding.apply {
            massTV.text = "Mass [kg]"
            heightTV.text = "Height [cm]"
            massET.text.clear()
            heightET.text.clear()
            bmiTV.text = "0.00"
        }
    }

    fun setColor(bmi: Double): Int? {
        var bmiColor: Int? = null
        binding.apply {
            if (bmi > 30 && bmi <= 40) {
                bmiTV.setTextColor(Color.parseColor("#DA1B0D"))
                bmiColor = Color.parseColor("#DA1B0D")
                section = 1
            }

            else if (bmi <= 30 && bmi > 25) {
                bmiTV.setTextColor(Color.parseColor("#D3D010"))
                bmiColor = Color.parseColor("#D3D010")
                section = 2
            }

            else if (bmi <= 25 && bmi > 18) {
                bmiTV.setTextColor(Color.parseColor("#08B64D"))
                bmiColor = Color.parseColor("#08B64D")
                section = 3
            }

            else if (bmi <= 18 && bmi > 16) {
                bmiTV.setTextColor(Color.parseColor("#0F21C9"))
                bmiColor = Color.parseColor("#0F21C9")
                section = 4
            }
            else {
                section = 5
            }
        }
        return bmiColor
    }
}