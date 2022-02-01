package com.example.menu

class BmiForKgCm : BMI {
    override fun countBMI(mass: Double, height: Double): Double {
        return (mass/(height*height))*10000
    }
}