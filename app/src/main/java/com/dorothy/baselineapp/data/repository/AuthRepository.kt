package com.dorothy.baselineapp.data.repository

import com.dorothy.baselineapp.data.models.UserModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

class AuthRepository : AuthService {

    private val supabase = createSupabaseClient(
        supabaseUrl = "https://jnauygiimqrlyilkougc.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpuYXV5Z2lpbXFybHlpbGtvdWdjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzczMzgzNTksImV4cCI6MjA5MjkxNDM1OX0.sDgeYTFiDEimahf_zGUDilhoYhw_Id846hNiJS6bz6k"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }

    override suspend fun registerUser(user: UserModel) {
        supabase.auth.signUpWith(Email) {
            email = user.email
            password = user.password
        }
    }

    override suspend fun loginUser(user: UserModel) {
        supabase.auth.signInWith(Email) {
            email = user.email
            password = user.password
        }
    }

    override suspend fun resetPassword(email: String) {
        supabase.auth.resetPasswordForEmail(email = email)
    }

    override suspend fun getUserProfile(user: UserModel) {
        // We'll update this to return actual user profile data if needed
        // For now, let's keep it as is or implement a basic fetch
    }

    override suspend fun getCurrentUserEmail(): String? {
        return try {
            supabase.auth.retrieveUserForCurrentSession().email
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun logoutUser() {
        supabase.auth.signOut()
    }
}
