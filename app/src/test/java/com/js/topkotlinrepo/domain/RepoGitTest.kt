package com.js.topkotlinrepo.domain

import com.js.topkotlinrepo.GetAllKotlinRepositoriesQuery
import com.js.topkotlinrepo.GetIssuesQuery
import com.js.topkotlinrepo.GetPullRequestsQuery
import com.js.topkotlinrepo.GetRepositoryInsightsByDateQuery
import com.js.topkotlinrepo.domain.repogitlist.toRepoGit
import com.js.topkotlinrepo.domain.repogitlist.toRepoGitDetail
import com.js.topkotlinrepo.domain.repogitlist.toRepoIssue
import com.js.topkotlinrepo.domain.repogitlist.toRepoPullRequest
import com.js.topkotlinrepo.type.IssueState
import com.js.topkotlinrepo.type.PullRequestState
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

class RepoGitTest {

    @Test
    fun `should map Repository to RepoGitDetailViewModel correctly`() {
        // Given
        val repoGit = GetRepositoryInsightsByDateQuery.Repository(
            name = "Repo",
            owner = GetRepositoryInsightsByDateQuery.Owner(login = "Anakin"),
            description = "desc",
            stargazers = GetRepositoryInsightsByDateQuery.Stargazers(totalCount = 9),
            issues = GetRepositoryInsightsByDateQuery.Issues(totalCount = 9),
            watchers = GetRepositoryInsightsByDateQuery.Watchers(totalCount = 9),
            forkCount = 9,
            pullRequests = GetRepositoryInsightsByDateQuery.PullRequests(totalCount = 9)
        )

        // When
        val result = repoGit.toRepoGitDetail()

        // Then
        result.name `should be` "Repo"
        result.owner `should be` "Anakin"
        result.description `should be` "desc"
        result.stars `should be` 9
        result.issues `should be` 9
        result.watchers `should be` 9
        result.forks `should be` 9
        result.pullRequests `should be` 9
    }

    @Test
    fun `should map Issue to RepoIssue correctly`() {
        // Given
        val repoGit = GetIssuesQuery.AsIssue(
            state = IssueState.OPEN,
            title = "Issue",
            number = 9
        )

        // When
        val result = repoGit.toRepoIssue()

        // Then
        result.state `should be` IssueState.OPEN
        result.title `should be` "Issue"
        result.number `should be` 9
    }

    @Test
    fun `should map PullRequest to RepoPullRequest correctly`() {
        // Given
        val pullRequest = GetPullRequestsQuery.AsPullRequest(
            state = PullRequestState.CLOSED,
            title = "PullRequest",
            number = 9
        )

        // When
        val result = pullRequest.toRepoPullRequest()

        // Then
        result.state `should be` PullRequestState.CLOSED
        result.title `should be` "PullRequest"
        result.number `should be` 9
    }

    @Test
    fun `should map GetAllKotlinRepositoriesQuery AsRepository to RepoGit correctly`() {
        // Given
        val repoGit = GetAllKotlinRepositoriesQuery.AsRepository(
            id = "abc",
            name = "Repo",
            owner = GetAllKotlinRepositoriesQuery.Owner(login = "Anakin"),
            description = "",
            stargazers = GetAllKotlinRepositoriesQuery.Stargazers(totalCount = 9)
        )

        // When
        val result = repoGit.toRepoGit()

        // Then
        result.id `should be` "abc"
        result.name `should be` "Repo"
        result.owner `should be` "Anakin"
        result.description `should be` ""
        result.stars `should be` 9
    }
}
