package com.js.topkotlinrepo.data.network

import com.js.topkotlinrepo.BuildConfig
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class AuthRepository {

    fun authenticate(code: String): AuthState {
        val client = OkHttpClient.Builder().build()
        val body = FormBody.Builder()
            .add("client_id", BuildConfig.GITHUB_CLIENT_ID)
            .add("client_secret", BuildConfig.GITHUB_CLIENT_SECRET)
            .add("code", code)
            .build()

        val request = Request.Builder()
            .url(BuildConfig.GITHUB_TOKEN_ENDPOINT)
            .addHeader("Accept", "application/json")
            .post(body)
            .build()

        val response = client.newCall(request).execute()
        return if (response.isSuccessful && response.body != null) {
            try {
                Success(JSONObject(response.body?.string()!!).optString("access_token", ""))
            } catch (e: Exception) {
                Error
            }
        } else {
            Error
        }
    }
}

sealed class AuthState

data class Success(val token: String) : AuthState()
object Error : AuthState()
