package com.dorothy.baselineapp.data.repository

import com.dorothy.baselineapp.data.models.UserModel


interface AuthService {
    suspend fun registerUser(user: UserModel)
    suspend fun loginUser(user: UserModel)
    suspend fun resetPassword(email: String)
    suspend fun getUserProfile(user: UserModel)
    suspend fun logoutUser()
}


