package com.js.topkotlinrepo.data

import com.js.topkotlinrepo.data.local.SessionRepository
import com.js.topkotlinrepo.data.local.SharedPrefsDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

class SessionRepositoryTest {

    val sharedPrefsDataSource = mock<SharedPrefsDataSource>()
    val sessionRepository = SessionRepository(sharedPrefsDataSource)

    @Test
    fun `should not be authenticated when only the email address is specified`() {
        // Given
        whenever(sharedPrefsDataSource.email).thenReturn("ana@kin.com")
        whenever(sharedPrefsDataSource.token).thenReturn("")

        // When
        val isAuthenticated = sessionRepository.isAuthenticated()

        // Then
        isAuthenticated `should be` false
    }

    @Test
    fun `should not be authenticated when the email address is null or blank`() {
        // Given
        whenever(sharedPrefsDataSource.email).thenReturn("")
        whenever(sharedPrefsDataSource.token).thenReturn("")

        // When
        val isAuthenticated = sessionRepository.isAuthenticated()

        // Then
        isAuthenticated `should be` false
    }

    @Test
    fun `should be authenticated when email address and token is not null and not empty`() {
        // Given
        whenever(sharedPrefsDataSource.email).thenReturn("ana@kin.fr")
        whenever(sharedPrefsDataSource.token).thenReturn("token")

        // When
        val isAuthenticated = sessionRepository.isAuthenticated()

        // Then
        isAuthenticated `should be` true
    }

    @Test
    fun `should clear shared preferences session related data`() {
        // When
        sessionRepository.clear()

        // Then
        verify(sharedPrefsDataSource).clearSession()
    }
}
