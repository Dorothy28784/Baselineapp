package com.dorothy.baselineapp.data.repository

import com.dorothy.baselineapp.data.Model.BloodPressure
import com.dorothy.baselineapp.data.local.BloodPressureDao
import com.dorothy.baselineapp.data.remote.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow

class BloodPressureRepository(private val bloodPressureDao: BloodPressureDao) {
    
    val allReadings: Flow<List<BloodPressure>> = bloodPressureDao.getAllReadings()

    suspend fun saveReading(bloodPressure: BloodPressure) {
        // Save to local Room DB first
        bloodPressureDao.insert(bloodPressure)
        
        // Attempt to sync with Supabase
        try {
            SupabaseClient.client.postgrest["blood_pressure"].insert(bloodPressure)
        } catch (e: Exception) {
            e.printStackTrace()
            // In a real app, you'd handle offline sync later
        }
    }
}
