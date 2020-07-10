package com.js.topkotlinrepo.ui

import com.js.topkotlinrepo.domain.repogitlist.RepoGit
import com.js.topkotlinrepo.domain.repogitlist.RepoGitDetail
import com.js.topkotlinrepo.domain.repogitlist.RepoIssue
import com.js.topkotlinrepo.domain.repogitlist.RepoPullRequest
import com.js.topkotlinrepo.type.IssueState
import com.js.topkotlinrepo.type.PullRequestState
import com.js.topkotlinrepo.ui.repogit.toRepoGitDetailViewModel
import com.js.topkotlinrepo.ui.repogit.toRepoIssueViewModel
import com.js.topkotlinrepo.ui.repogit.toRepoPullRequestViewModel
import com.js.topkotlinrepo.ui.repogitlist.toRepoGitItemViewModel
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

class RepoGitViewModelsTest {

    @Test
    fun `should map RepoGitDetail to RepoGitDetailViewModel correctly`() {
        // Given
        val repoGit = RepoGitDetail("Repo", "Anakin", "", 9, 9, 9, 9, 9)

        // When
        val result = repoGit.toRepoGitDetailViewModel()

        // Then
        result.name `should be` "Repo"
        result.owner `should be` "Anakin"
        result.description `should be` ""
        result.stars `should be` 9
        result.issues `should be` 9
        result.watchers `should be` 9
        result.forks `should be` 9
        result.pullRequests `should be` 9
    }

    @Test
    fun `should map RepoIssue to RepoIssueViewModel correctly`() {
        // Given
        val issue = RepoIssue(IssueState.CLOSED, "Issue", 9)

        // When
        val result = issue.toRepoIssueViewModel()

        // Then
        result.state `should be` "CLOSED"
        result.title `should be` "Issue"
        result.number `should be` 9
    }

    @Test
    fun `should map RepoPullRequest to RepoPullRequestViewModel correctly`() {
        // Given
        val pullRequest = RepoPullRequest(PullRequestState.CLOSED, "PullRequest", 9)

        // When
        val result = pullRequest.toRepoPullRequestViewModel()

        // Then
        result.state `should be` "CLOSED"
        result.title `should be` "PullRequest"
        result.number `should be` 9
    }

    @Test
    fun `should map RepoGit to RepoGitItemViewModel correctly`() {
        // Given
        val repoGit = RepoGit("abc", "Repo", "Anakin", "", 9)

        // When
        val result = repoGit.toRepoGitItemViewModel {  }

        // Then
        result.id `should be` "abc"
        result.name `should be` "Repo"
        result.owner `should be` "Anakin"
        result.description `should be` ""
        result.stars `should be` 9
    }
}
