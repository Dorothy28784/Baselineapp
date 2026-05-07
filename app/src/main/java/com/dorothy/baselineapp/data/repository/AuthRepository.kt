package com.dorothy.baselineapp.data.repository

import android.R.attr.password
import android.net.http.HttpResponseCache.install
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import com.dorothy.baselineapp.data.models.UserModel
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.Storage
import java.util.Locale.filter
import javax.security.auth.callback.PasswordCallback


class AuthRepository : AuthService {

    val supabase = createSupabaseClient(

        supabaseUrl = "https://jnauygiimqrlyilkougc.supabase.co/rest/v1/",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpuYXV5Z2lpbXFybHlpbGtvdWdjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzczMzgzNTksImV4cCI6MjA5MjkxNDM1OX0.sDgeYTFiDEimahf_zGUDilhoYhw_Id846hNiJS6bz6k"

    ) {

        install(Auth)

        install(Postgrest)

        install(Storage)

    }
    override suspend fun registerUser(userDetails: UserModel) {

        supabase.auth.signUpWith(Email) {
            email = userDetails.email
            Password = userDetails.password
        }









    override suspend fun resetPassword(email: String) {
        supabase.auth.resetPasswordForEmail(email = email)
    }

    override suspend fun getUserProfile(user: UserModel) {
//        TODO("Not yet implemented")
    }

    override suspend fun logoutUser() {
        supabase.auth.signOut()
    }

}



}