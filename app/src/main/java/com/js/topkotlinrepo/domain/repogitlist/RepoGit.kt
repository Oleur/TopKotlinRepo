package com.js.topkotlinrepo.domain.repogitlist

import com.js.topkotlinrepo.GetAllKotlinRepositoriesQuery
import com.js.topkotlinrepo.GetIssuesQuery
import com.js.topkotlinrepo.GetPullRequestsQuery
import com.js.topkotlinrepo.GetRepositoryInsightsByDateQuery
import com.js.topkotlinrepo.type.IssueState
import com.js.topkotlinrepo.type.PullRequestState

data class RepoGit(
    val id: String,
    val name: String,
    val owner: String,
    val description: String?,
    val stars: Int
)

data class RepoGitDetail(
    val name: String,
    val owner: String,
    val description: String?,
    val stars: Int,
    val issues: Int,
    val watchers: Int,
    val forks: Int,
    val pullRequests: Int
)

data class RepoIssue(
    val state: IssueState,
    val title: String,
    val number: Int
)

data class RepoPullRequest(
    val state: PullRequestState,
    val title: String,
    val number: Int
)

// Mappers
fun GetRepositoryInsightsByDateQuery.Repository.toRepoGitDetail(): RepoGitDetail {
    return RepoGitDetail(name, owner.login, description, stargazers.totalCount,
        issues.totalCount, watchers.totalCount, forkCount, pullRequests.totalCount)
}

fun GetIssuesQuery.AsIssue.toRepoIssue(): RepoIssue {
    return RepoIssue(state, title, number)
}

fun GetPullRequestsQuery.AsPullRequest.toRepoPullRequest(): RepoPullRequest {
    return RepoPullRequest(state, title, number)
}

fun GetAllKotlinRepositoriesQuery.AsRepository.toRepoGit(): RepoGit {
    return RepoGit(id, name, owner.login, description, stargazers.totalCount)
}
