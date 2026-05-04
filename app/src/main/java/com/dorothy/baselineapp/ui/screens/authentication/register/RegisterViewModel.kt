package com.dorothy.baselineapp.ui.screens.authentication.register

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.websocket.Frame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
   

data class RegistrationUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

class RegistrationViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(RegistrationUiState())

    val uiState: StateFlow<RegistrationUiState> = _uiState


    fun registerUser(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = RegistrationUiState(errorMessage = "Fields cannot be empty")
            return
        }


        _uiState.value = RegistrationUiState(isLoading = true)

        viewModelScope.launch {
            try {
                // Simulate a network call delay
                kotlinx.coroutines.delay(2000)

                // On success
                _uiState.value = RegistrationUiState(isSuccess = true)
            } catch (e: Exception) {
                // On error
                _uiState.value = RegistrationUiState(errorMessage = "Registration failed: ${e.message}")
            }
        }
    }
}




@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {
        CircularProgressIndicator()
    }

    if (state.isSuccess) {
        Frame.Text("Registration Complete!")
    }

    // Call viewModel.registerUser(email, password) on button click
}

fun viewModel(): RegistrationViewModel {
    val todo = TODO("Not yet implemented")
}



