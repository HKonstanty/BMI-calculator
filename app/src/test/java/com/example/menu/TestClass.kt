package com.example.menu

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.Test
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.math.roundToInt

class TestClass : FunSpec() {

    init {
        bmiForEU()
        bmiForGB()
    }
    @Test
    fun bmiForGB() {
        val bmi = BmiForGB()
        var countedBMI = bmi.countBMI(200.0, 60.0)
        (countedBMI * 100.0).roundToInt() / 100.0 shouldBe  39.06

        countedBMI = bmi.countBMI(250.0, 70.0)
        (countedBMI * 100.0).roundToInt() / 100.0 shouldBe  35.87

        countedBMI = bmi.countBMI(180.0, 70.0)
        (countedBMI * 100.0).roundToInt() / 100.0 shouldNotBe   35.87
    }

    @Test
    fun bmiForEU() {
        val bmi = BmiForKgCm()
        var countedBMI = bmi.countBMI(70.0, 170.0)
        (countedBMI * 100.0).roundToInt() / 100.0 shouldBe 24.22

        countedBMI = bmi.countBMI(110.0, 188.0)
        (countedBMI * 100.0).roundToInt() / 100.0 shouldBe 31.12

        countedBMI = bmi.countBMI(87.0, 170.0)
        (countedBMI * 100.0).roundToInt() / 100.0 shouldNotBe 24.22
    }
}