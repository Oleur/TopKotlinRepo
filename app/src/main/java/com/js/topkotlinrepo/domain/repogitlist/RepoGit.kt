package com.js.topkotlinrepo.domain.repogitlist

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
