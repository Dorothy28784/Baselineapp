package com.dorothy.baselineapp.ui.screens.authentication.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorothy.baselineapp.data.Model.UserModel
import com.dorothy.baselineapp.data.repository.AuthRepository
import com.dorothy.baselineapp.data.repository.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

class LoginViewModel(
    private val authService: AuthService = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun loginUser(user: UserModel) {
        if (user.email.isBlank() || user.password.isBlank()) {
            _uiState.value = LoginUiState(errorMessage = "Fields cannot be empty")
            return
        }

        _uiState.value = LoginUiState(isLoading = true)

        viewModelScope.launch {
            try {
                // Consistency with RegisterViewModel: lowercase password for Supabase compatibility
                val userToLogin = user.copy(password = user.password.lowercase())
                authService.loginUser(userToLogin)
                _uiState.value = LoginUiState(isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = LoginUiState(errorMessage = "Login failed: ${e.message}")
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}
