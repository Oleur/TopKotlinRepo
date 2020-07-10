package com.js.topkotlinrepo.ui.login

sealed class LoginState

data class DisplayError(val error: String) : LoginState()
object NavigateToMain : LoginState()
object NavigateToLogin : LoginState()
object NavigateToAuth : LoginState()
