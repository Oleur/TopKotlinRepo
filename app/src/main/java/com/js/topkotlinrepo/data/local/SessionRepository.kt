package com.js.topkotlinrepo.data.local

class SessionRepository(private val sharedPrefs: SharedPrefsDataSource) {

    var email
        get() = sharedPrefs.email
        set(value) {
            sharedPrefs.email = value
        }

    var token
        get() = sharedPrefs.token
        set(value) {
            sharedPrefs.token = value
        }

    fun isAuthenticated(): Boolean {
        return sharedPrefs.email.isNotEmpty() && sharedPrefs.token.isNotEmpty()
    }

    fun clear() {
        sharedPrefs.clearSession()
    }
}
