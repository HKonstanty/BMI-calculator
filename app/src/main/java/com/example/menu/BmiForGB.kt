package com.example.menu

class BmiForGB : BMI {
    override fun countBMI(mass: Double, height: Double): Double {
        return mass/(height*height)*703
    }
}