package com.example.myquizapps.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences("PREFS_TOKEN_FILE", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString("USER_TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString("USER_TOKEN", null)
    }

    fun saveQuizScore(coin: Int) {
        val editor = prefs.edit()
        editor.putInt("quizCoin", coin)
        editor.apply()
    }

    fun getQuizScore():Int? {
     return prefs.getInt("quizCoin", 0)
    }
}