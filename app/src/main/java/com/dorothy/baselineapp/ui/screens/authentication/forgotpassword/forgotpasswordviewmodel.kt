package com.dorothy.baselineapp.ui.screens.authentication.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorothy.baselineapp.data.repository.AuthRepository
import com.dorothy.baselineapp.data.repository.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ForgotPasswordUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

class ForgotPasswordViewModel(
    private val authService: AuthService = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState

    fun resetPassword(email: String) {
        if (email.isBlank()) {
            _uiState.value = ForgotPasswordUiState(errorMessage = "Email cannot be empty")
            return
        }

        _uiState.value = ForgotPasswordUiState(isLoading = true)

        viewModelScope.launch {
            try {
                authService.resetPassword(email)
                _uiState.value = ForgotPasswordUiState(isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = ForgotPasswordUiState(errorMessage = "Reset failed: ${e.message}")
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}
