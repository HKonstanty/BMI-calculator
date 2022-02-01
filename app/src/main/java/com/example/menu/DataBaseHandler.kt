package com.example.menu

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DARABASE_NAME = "MyDB"
val TABLE_NAME = "Measurement"
val COL_HEIGHT = "height"
val COL_MASS = "mass"
val COL_BMI = "bmi"
val COL_BMI_COLOR = "bmi_color"
val COL_DATE = "date"

class DataBaseHandler(context: Context) : SQLiteOpenHelper(context, DARABASE_NAME, null, 1){
    private val context: Context = context
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_HEIGHT + " VARCHAR(256)," +
                COL_MASS + " VARCHAR(256)," +
                COL_BMI + " VARCHAR(256)," +
                COL_BMI_COLOR + " INTEGER," +
                COL_DATE + " VARCHAR(256))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(measure: Measurement) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_HEIGHT, measure.height)
        cv.put(COL_MASS, measure.mass)
        cv.put(COL_BMI, measure.bmi)
        cv.put(COL_BMI_COLOR, measure.bmiColor)
        cv.put(COL_DATE, measure.date)

        var result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Field", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readData(): ArrayList<Measurement> {
        var list : ArrayList<Measurement> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var measure = Measurement()
                measure.height = result.getString(result.getColumnIndex(COL_HEIGHT))
                measure.mass = result.getString(result.getColumnIndex(COL_MASS))
                measure.bmi = result.getString(result.getColumnIndex(COL_BMI))
                measure.bmiColor = result.getString(result.getColumnIndex(COL_BMI_COLOR)).toInt()
                measure.date = result.getString(result.getColumnIndex(COL_DATE))
                list.add(0, measure)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }
}