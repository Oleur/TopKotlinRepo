package com.js.topkotlinrepo.ui.repogit

import com.js.topkotlinrepo.domain.repogitlist.RepoGitDetail
import com.js.topkotlinrepo.domain.repogitlist.RepoIssue
import com.js.topkotlinrepo.domain.repogitlist.RepoPullRequest
import com.js.topkotlinrepo.type.IssueState
import com.js.topkotlinrepo.type.PullRequestState
import java.text.SimpleDateFormat
import java.util.*

data class RepoGitDetailViewModel(
    val name: String,
    val owner: String,
    val description: String?,
    val stars: Int,
    val issues: Int,
    val watchers: Int,
    val forks: Int,
    val pullRequests: Int
)

data class RepoIssueViewModel(
    val state: String,
    val title: String,
    val number: Int
) {

    fun isIssueClosed() = state.equals(IssueState.CLOSED.name, ignoreCase = true)
}

data class RepoPullRequestViewModel(
    val state: String,
    val title: String,
    val number: Int
) {

    fun isIssueClosed() = state.equals(PullRequestState.CLOSED.name, ignoreCase = true)
}

data class DateRange(
    val startDate: Date,
    val endDate: Date
)

// Mappers
fun RepoGitDetail.toRepoGitDetailViewModel(): RepoGitDetailViewModel {
    return RepoGitDetailViewModel(name, owner, description, stars, issues, watchers, forks, pullRequests)
}

fun RepoIssue.toRepoIssueViewModel(): RepoIssueViewModel {
    return RepoIssueViewModel(state.name, title, number)
}

fun RepoPullRequest.toRepoPullRequestViewModel(): RepoPullRequestViewModel {
    return RepoPullRequestViewModel(state.name, title, number)
}

fun Date.format(): String = SimpleDateFormat("YYYY-MM-dd", Locale.US).format(this)
