package com.dorothy.baselineapp.data.ViewModel

import androidx.lifecycle.ViewModel
import com.dorothy.baselineapp.data.Model.BloodSugar

class BloodPressure : ViewModel () {
    private val bloodPressureList = mutableListOf<BloodPressure>()
    private val bloodSugarList = mutableListOf<BloodSugar>()
    fun addBloodPressure(pressure: BloodPressure) {
        bloodPressureList.add(pressure)
    }

    fun addBloodSugar(sugar: BloodSugar) {
        bloodSugarList.add(sugar)
    }

    fun getBloodPressureList(): List<BloodPressure> {
        return bloodPressureList
    }

    fun getBloodSugarList(): List<BloodSugar> {
        return bloodSugarList
    }

    fun saveReadings() {
    }
}