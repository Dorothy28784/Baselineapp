package com.dorothy.baselineapp.data.Model

data class BloodPressure(
    val systolic: Int,
    val diastolic: Int,
    val pulse: Int,
    val timestamp: Long = System.currentTimeMillis()
)

data class BloodSugar(
    val level: Int,
    val timestamp: Long = System.currentTimeMillis()
)