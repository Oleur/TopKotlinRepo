package com.js.topkotlinrepo.core.di.modules

import com.js.topkotlinrepo.data.local.SessionRepository
import com.js.topkotlinrepo.data.local.SharedPrefsDataSource
import com.js.topkotlinrepo.data.network.AuthRepository
import com.js.topkotlinrepo.data.network.RepoGitRepository
import org.koin.dsl.module

val dataModule = module {
    single { SharedPrefsDataSource(get()) }
    single { SessionRepository(get()) }
    single { RepoGitRepository(get()) }
    single { AuthRepository() }
}
