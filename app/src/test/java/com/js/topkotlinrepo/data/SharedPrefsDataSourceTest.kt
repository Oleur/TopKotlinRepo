package com.js.topkotlinrepo.data

import android.content.SharedPreferences
import com.js.topkotlinrepo.data.local.SharedPrefsDataSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

class SharedPrefsDataSourceTest {

    val editor = mock<SharedPreferences.Editor>()
    val sharedPreferences = mock<SharedPreferences> {
        on { edit() } doReturn editor
    }
    val sharedPrefsDataSource = SharedPrefsDataSource(sharedPreferences)

    @Test
    fun `should remove email data in shared preferences synchronously when clearing session`() {
        // When
        sharedPrefsDataSource.clearSession()

        // Then
        verify(editor).remove("email")
        verify(editor).commit()
    }
}
