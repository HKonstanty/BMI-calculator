package com.example.menu

class Measurement {

    var height: String = ""
    var mass: String = ""
    var bmi: String = ""
    var bmiColor: Int = 0
    var date: String = ""

    constructor(height: String, mass: String, bmi: String, bmiColor: Int, date: String) {
        this.height = height
        this.mass = mass
        this.bmi = bmi
        this.bmiColor = bmiColor
        this.date = date
    }

    constructor()
}