package com.js.topkotlinrepo.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.topkotlinrepo.domain.login.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {

    val state = MutableLiveData<LoginState>()
    val userName = MutableLiveData<String>()

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val loginOk = useCase.login(userName.value)
            if (loginOk) {
                state.postValue(NavigateToAuth)
            } else {
                state.postValue(DisplayError("Email address should be valid!"))
            }
        }
    }

    fun authenticate(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val auth = useCase.authenticate(code)
            if (auth) {
                state.postValue(NavigateToMain)
            } else {
                useCase.clearSession()
                state.postValue(NavigateToLogin)
            }
        }
    }

    fun isAuthenticate() {
        viewModelScope.launch(Dispatchers.IO) {
            if (useCase.isUserAuthenticated()) {
                state.postValue(NavigateToMain)
            } else {
                state.postValue(NavigateToLogin)
            }
        }
    }
}
