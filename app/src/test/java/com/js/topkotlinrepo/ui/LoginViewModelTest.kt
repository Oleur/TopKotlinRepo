package com.js.topkotlinrepo.ui

import com.js.topkotlinrepo.domain.login.LoginUseCase
import com.js.topkotlinrepo.test.InstantExecutorExtension
import com.js.topkotlinrepo.test.getOrAwaitValue
import com.js.topkotlinrepo.ui.login.LoginViewModel
import com.js.topkotlinrepo.ui.login.NavigateToAuth
import com.js.topkotlinrepo.ui.login.NavigateToMain
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class LoginViewModelTest {

    val useCase = mock<LoginUseCase>()

    @Test
    fun `should set state when passing a state`() {
        // Given
        val viewModel = LoginViewModel(useCase)

        // When
        viewModel.state.value = NavigateToMain

        // Then
        viewModel.state.value `should be` NavigateToMain
    }

    @Test
    fun `should go to Authentication if login is ok`() {
        // Given
        val viewModel = LoginViewModel(useCase)
        viewModel.emailAddress.value = "ana@kin.sky"
        whenever(useCase.login("ana@kin.sky")).thenReturn(true)

        // When
        viewModel.login()

        // Then
        viewModel.state.getOrAwaitValue() `should be` NavigateToAuth
    }

    @Test
    fun `should go to main fragment when authentication is ok`() {
        // Given
        val viewModel = LoginViewModel(useCase)
        whenever(useCase.authenticate("code")).thenReturn(true)

        // When
        viewModel.authenticate("code")

        // Then
        viewModel.state.getOrAwaitValue() `should be` NavigateToMain
    }
}
