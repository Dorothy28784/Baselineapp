package com.dorothy.baselineapp.data.local

import androidx.room.*
import com.dorothy.baselineapp.data.Model.BloodPressure
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodPressureDao {
    @Query("SELECT * FROM blood_pressure ORDER BY timestamp DESC")
    fun getAllReadings(): Flow<List<BloodPressure>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bloodPressure: BloodPressure)

    @Delete
    suspend fun delete(bloodPressure: BloodPressure)
}
