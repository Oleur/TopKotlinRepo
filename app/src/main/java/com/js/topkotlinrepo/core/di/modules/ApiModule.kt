package com.js.topkotlinrepo.core.di.modules

import com.apollographql.apollo.ApolloClient
import com.js.topkotlinrepo.BuildConfig
import com.js.topkotlinrepo.data.local.SessionRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module

val apiModule = module {
    single { provideApolloClient(get()) }
}

private fun provideOkHttpClient(sessionRepository: SessionRepository): OkHttpClient {
    val networkInterceptor = NetworkInterceptor(sessionRepository)
    return OkHttpClient.Builder().addInterceptor(networkInterceptor).build()
}

private class NetworkInterceptor(private val sessionRepository: SessionRepository) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .header("Authorization", "Bearer ${sessionRepository.token}")
                .build()
        )
    }
}

private fun provideApolloClient(sessionRepository: SessionRepository): ApolloClient {
    return ApolloClient.builder()
        .serverUrl(BuildConfig.GITHUB_API)
        .okHttpClient(provideOkHttpClient(sessionRepository))
        .build()
}
