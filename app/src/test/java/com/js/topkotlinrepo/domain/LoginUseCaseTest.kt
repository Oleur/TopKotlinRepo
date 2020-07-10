package com.js.topkotlinrepo.domain

import com.js.topkotlinrepo.data.local.SessionRepository
import com.js.topkotlinrepo.data.local.SharedPrefsDataSource
import com.js.topkotlinrepo.data.network.AuthRepository
import com.js.topkotlinrepo.data.network.Error
import com.js.topkotlinrepo.data.network.Success
import com.js.topkotlinrepo.domain.login.LoginUseCase
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever

class LoginUseCaseTest {

    val sharedPrefsDataSource = mock<SharedPrefsDataSource>()
    val sessionRepository = SessionRepository(sharedPrefsDataSource)
    val authRepository = mock<AuthRepository>()
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

    @Test
    fun `should authentication be ok`() {
        // Given
        val code = "code"
        whenever(authRepository.authenticate(code)).thenReturn(Success("token"))

        // When
        val result = useCase.authenticate(code)

        // Then
        result `should be` true
    }

    @Test
    fun `should authentication not be successful`() {
        // Given
        val code = "code"
        whenever(authRepository.authenticate(code)).thenReturn(Error)

        // When
        val result = useCase.authenticate(code)

        // Then
        result `should be` false
    }
}
