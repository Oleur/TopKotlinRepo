package com.js.topkotlinrepo.core.di.modules

import com.js.topkotlinrepo.domain.login.LoginUseCase
import com.js.topkotlinrepo.domain.repogitlist.RepoGitUseCase
import org.koin.dsl.module

val domainModule = module {
    single { LoginUseCase(get(), get()) }
    single { RepoGitUseCase(get()) }
}
