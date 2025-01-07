package com.example.sushiv.Model

import android.content.Context

class sessionManager(var context: Context) {private var sharedPreferences = context.getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    companion object {
        private const val IS_LOGGED_IN = "IsLoggedIn"
        private const val KEY_EMAIL = "email"
    }

    fun createLoginSession(email: String) {
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.putString(KEY_EMAIL, email)
        editor.commit()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
    }
}
