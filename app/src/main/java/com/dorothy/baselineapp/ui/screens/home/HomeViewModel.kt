package com.dorothy.baselineapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorothy.baselineapp.data.repository.AuthRepository
import com.dorothy.baselineapp.data.repository.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val userEmail: String? = null,
    val isLoading: Boolean = false
)

class HomeViewModel(
    private val authService: AuthService = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchCurrentUser()
    }

    private fun fetchCurrentUser() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val email = authService.getCurrentUserEmail()
            _uiState.value = _uiState.value.copy(
                userEmail = email,
                isLoading = false
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            authService.logoutUser()
        }
    }
}
