package com.js.topkotlinrepo.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefsDataSource(private val sharedPreferences: SharedPreferences) {

    companion object {

        const val TOPKOTLINREPO_PREFS = "TopKotlinRepoPrefs"

        private const val SESSION_EMAIL = "email"
        private const val SESSION_TOKEN = "token"
    }

    var email by pref(SESSION_EMAIL, "")
    var token by pref(SESSION_TOKEN, "")

    fun clearSession() = remove(SESSION_EMAIL, SESSION_TOKEN, commit = true)

    private fun <T> pref(key: String, defValue: T) = sharedPreferences.delegate(key, defValue)

    private fun remove(vararg keys: String, commit: Boolean = false) {
        sharedPreferences.edit(commit) {
            keys.forEach { key ->
                remove(key)
            }
        }
    }
}
