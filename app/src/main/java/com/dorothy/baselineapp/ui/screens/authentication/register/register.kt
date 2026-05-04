package com.dorothy.baselineapp.ui.screens.authentication.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController


@Composable
fun RegisterScreen(
    navController: NavHostController,
    registerViewModel: RegisterViewModel = viewModel(),
    modifier: Modifier
){
    val isLoading = registerViewModel.isLoading.collectAsState()
    val responseMessage = registerViewModel.message.collectAsState()
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember{ mutableStateOf(TextFieldValue("")) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = {password =it},
            label = {Text(text ="password")},
            maxLines = 1
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email =it},
            label = {Text(text ="email")},
            minLines = 3
        )
        HorizontalDivider()
        Text(text = responseMessage.value)
        Text(text = isLoading.value.toString())
        HorizontalDivider()
        if(isLoading.value){
            CircularProgressIndicator()
        }else{
            OutlinedButton(
                onClick = {
                    val user = UserModel(
                        email = email.text,
                        password = password.text
                    )
                    registerViewModel.registerUser(user)
                }
            ) {
                Text(text="create account")
            }
        }


        HorizontalDivider()
    }
}