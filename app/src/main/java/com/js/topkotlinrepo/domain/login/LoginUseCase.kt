package com.js.topkotlinrepo.domain.login

import com.js.topkotlinrepo.data.local.SessionRepository
import com.js.topkotlinrepo.data.network.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository,
                   private val sessionRepository: SessionRepository) {

    companion object {
        private val REGEX_EMAIL_ADDRESS = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+".toRegex()
    }

    fun isUserAuthenticated() = sessionRepository.isAuthenticated()

    fun login(email: String?) = if (isEmailValid(email)) {
        sessionRepository.email = email!!
        true
    } else {
        false
    }

    fun authenticate(code: String): Boolean {
        val token = authRepository.authenticate(code)
        return if (token.isNotEmpty()) {
            sessionRepository.token = token
            true
        } else {
            false
        }
    }

    fun clearSession() {
        sessionRepository.clear()
    }

    private fun isEmailValid(email: String?): Boolean {
        return !email.isNullOrBlank() && email.matches(REGEX_EMAIL_ADDRESS)
    }
}
