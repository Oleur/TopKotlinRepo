package com.js.topkotlinrepo.ui.common

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.js.topkotlinrepo.ui.login.DisplayError
import com.js.topkotlinrepo.ui.login.LoginState

@BindingAdapter("error")
fun TextInputLayout.setErrorMessage(state: LoginState?) {
    if (state != null && state is DisplayError) {
        error = state.error
    } else {
        isErrorEnabled = false
    }
}
