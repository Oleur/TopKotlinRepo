package com.js.topkotlinrepo.ui

import com.js.topkotlinrepo.test.InstantExecutorExtension
import com.js.topkotlinrepo.ui.repogit.RepoIssueViewModel
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class RepoGitDetailViewModelTest {

    @Test
    fun `should issue be not be closed`() {
        // Given
        val viewModel = RepoIssueViewModel("", "", 0)

        // When
        val closed = viewModel.isIssueClosed()

        // Then
        closed `should be` false
    }

    @Test
    fun `should issue be be closed`() {
        // Given
        val viewModel = RepoIssueViewModel("CLOSED", "", 0)

        // When
        val closed = viewModel.isIssueClosed()

        // Then
        closed `should be` true
    }
}
