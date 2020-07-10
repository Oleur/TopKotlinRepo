package com.js.topkotlinrepo.core

import android.content.Context
import com.js.topkotlinrepo.core.di.modules.provideSharedPreferences
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.mockito.Answers

class AndroidModuleTest {

    val context = mock<Context> {
        on { getSharedPreferences(any(), any()) } doAnswer (Answers.RETURNS_DEEP_STUBS)
    }

    @Test
    fun `default shared preferences name should be TopKotlinRepoPrefs`() {
        // When
        provideSharedPreferences(context)

        // Then
        verify(context).getSharedPreferences(eq("TopKotlinRepoPrefs"), any())
    }
}
