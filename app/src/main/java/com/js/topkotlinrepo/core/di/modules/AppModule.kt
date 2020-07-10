package com.js.topkotlinrepo.core.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.js.topkotlinrepo.data.local.SharedPrefsDataSource.Companion.TOPKOTLINREPO_PREFS
import com.js.topkotlinrepo.ui.login.LoginViewModel
import com.js.topkotlinrepo.ui.repogit.RepoGitViewModel
import com.js.topkotlinrepo.ui.repogitlist.RepoGitListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideSharedPreferences(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { RepoGitListViewModel(get()) }
    viewModel { RepoGitViewModel(get()) }
}

@VisibleForTesting
fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(TOPKOTLINREPO_PREFS, Context.MODE_PRIVATE)
}
