package com.js.topkotlinrepo.domain

import com.js.topkotlinrepo.data.network.RepoGitRepository
import com.js.topkotlinrepo.domain.repogitlist.RepoGitUseCase
import com.nhaarman.mockitokotlin2.mock
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*


class RepoGitUseCaseTest {

    val repository = mock<RepoGitRepository>()
    val useCase = RepoGitUseCase(repository)

    @Test
    fun `should date array contains 52 weeks`() {
        // Given
        val dates = useCase.getDates()

        // When
        // Then
        dates.size `should be equal to` 52
    }

    @Test
    fun `should end date of the first range should match today date`() {
        // Given
        val dates = useCase.getDates()
        val firstDate = dates[0].endDate

        // When
        // Then
        isSameDay(firstDate, Date()) `should be` true
    }

    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val fmt = SimpleDateFormat("yyyyMMdd")
        return fmt.format(date1) == fmt.format(date2)
    }
}
