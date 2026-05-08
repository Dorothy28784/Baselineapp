package com.dorothy.baselineapp.data.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dorothy.baselineapp.data.Model.BloodPressure
import com.dorothy.baselineapp.data.local.AppDatabase
import com.dorothy.baselineapp.data.repository.BloodPressureRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BloodPressureUiState(
    val isLoading: Boolean = false,
    val isSaveSuccess: Boolean = false,
    val errorMessage: String? = null,
    val readings: List<BloodPressure> = emptyList()
)

class BloodPressureViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BloodPressureRepository
    
    private val _uiState = MutableStateFlow(BloodPressureUiState())
    val uiState: StateFlow<BloodPressureUiState> = _uiState.asStateFlow()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = BloodPressureRepository(database.bloodPressureDao())
        
        viewModelScope.launch {
            repository.allReadings.collect { readings ->
                _uiState.update { it.copy(readings = readings) }
            }
        }
    }

    fun saveReading(reading: BloodPressure) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                repository.saveReading(reading)
                _uiState.update { it.copy(isLoading = false, isSaveSuccess = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Failed to save reading") }
            }
        }
    }

    fun resetSaveSuccess() {
        _uiState.update { it.copy(isSaveSuccess = false) }
    }
}
