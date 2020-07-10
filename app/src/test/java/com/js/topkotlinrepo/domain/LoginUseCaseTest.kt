package com.js.topkotlinrepo.domain


import com.js.topkotlinrepo.data.local.SessionRepository
import com.js.topkotlinrepo.data.local.SharedPrefsDataSource
import com.js.topkotlinrepo.data.network.AuthRepository
import com.js.topkotlinrepo.domain.login.LoginUseCase
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test
import com.nhaarman.mockitokotlin2.mock

class LoginUseCaseTest {

    val sharedPrefsDataSource = mock<SharedPrefsDataSource>()
    val sessionRepository = SessionRepository(sharedPrefsDataSource)
    val authRepository = AuthRepository()
    val useCase = LoginUseCase(authRepository, sessionRepository)

    @Test
    fun `should email address be valid`() {
        // Given
        val email = "anakin@sky.walker"

        // When
        val result = useCase.login(email)

        // Then
        result `should be`  true
    }

    @Test
    fun `should email address be invalid`() {
        // Given
        val userName = "anafef.fr"

        // When
        val result = useCase.login(userName)

        // Then
        result `should be` false
    }
}
