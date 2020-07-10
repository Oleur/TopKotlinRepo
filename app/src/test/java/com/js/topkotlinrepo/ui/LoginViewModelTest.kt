package com.js.topkotlinrepo.ui

import com.js.topkotlinrepo.domain.login.LoginUseCase
import com.js.topkotlinrepo.test.InstantExecutorExtension
import com.js.topkotlinrepo.ui.login.LoginViewModel
import com.js.topkotlinrepo.ui.login.NavigateToMain
import com.nhaarman.mockitokotlin2.mock
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
}
