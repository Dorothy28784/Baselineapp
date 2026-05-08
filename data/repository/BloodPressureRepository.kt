package com.dorothy.baselineapp.data.repository

import com.dorothy.baselineapp.data.Model.BloodPressure
import com.dorothy.baselineapp.data.local.BloodPressureDao
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

interface BloodPressureRepository {
    suspend fun saveBloodPressure(reading: BloodPressure)
    fun getAllBloodPressureReadings(): Flow<List<BloodPressure>>
    suspend fun syncReadings()
}

class BloodPressureRepositoryImpl(
    private val supabase: SupabaseClient,
    private val bloodPressureDao: BloodPressureDao
) : BloodPressureRepository {

    override suspend fun saveBloodPressure(reading: BloodPressure) {
        withContext(Dispatchers.IO) {
            // Save to local Room DB first for offline support
            bloodPressureDao.insertReading(reading)
            
            // Then try to save to Supabase
            try {
                supabase.postgrest["blood_pressure"].insert(reading)
            } catch (e: Exception) {
                // Handle or log error, maybe mark for later sync
                e.printStackTrace()
            }
        }
    }

    override fun getAllBloodPressureReadings(): Flow<List<BloodPressure>> {
        return bloodPressureDao.getAllReadings()
    }

    override suspend fun syncReadings() {
        withContext(Dispatchers.IO) {
            try {
                val remoteReadings = supabase.postgrest["blood_pressure"]
                    .select()
                    .decodeList<BloodPressure>()
                bloodPressureDao.insertAll(remoteReadings)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
