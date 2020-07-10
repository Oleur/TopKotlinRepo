package com.js.topkotlinrepo.ui.repogit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.topkotlinrepo.domain.repogitlist.RepoGitDetail
import com.js.topkotlinrepo.domain.repogitlist.RepoGitUseCase
import com.js.topkotlinrepo.domain.repogitlist.RepoIssue
import com.js.topkotlinrepo.domain.repogitlist.RepoPullRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RepoGitViewModel(private val useCase: RepoGitUseCase) : ViewModel() {

    val loading = MutableLiveData(true)
    val repoGit = MutableLiveData<RepoGitDetailViewModel?>()
    val issues = MutableLiveData<List<RepoIssueViewModel>>()
    val pullRequests = MutableLiveData<List<RepoPullRequestViewModel>>()
    val startDate = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()

    var sortDates: List<DateRange> = emptyList()

    fun loadGitRepository(name: String, owner: String) {
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            sortDates = useCase.getDates()

            val repo = useCase.getGitRepository(name, owner)
            repoGit.postValue(mapViewModel(repo))

            val dateRange = sortDates[0]
            loadIssuesAndPullRequests(name, owner, dateRange.startDate, dateRange.endDate)

            startDate.postValue(dateRange.startDate.format())
            endDate.postValue(dateRange.endDate.format())

            loading.postValue(false)
        }
    }

    fun loadIssuesAndPullRequests(name: String, owner: String, start: Date, end: Date) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            startDate.postValue(start.format())
            endDate.postValue(end.format())

            val repoIssues = useCase.getIssuesCreatedByDateRange(name, owner, start.format(), end.format())
            issues.postValue(mapRepoIssues(repoIssues))

            val repoPullRequests = useCase.getPullRequestsCreatedByDateRange(name, owner, start.format(), end.format())
            pullRequests.postValue(mapRepoPullRequests(repoPullRequests))
            loading.postValue(false)
        }
    }

    private fun mapViewModel(repoGit: RepoGitDetail?): RepoGitDetailViewModel? {
        return repoGit?.toRepoGitDetailViewModel()
    }

    private fun mapRepoIssues(issues: List<RepoIssue>) = issues.map { issue ->
        issue.toRepoIssueViewModel()
    }

    private fun mapRepoPullRequests(issues: List<RepoPullRequest>) = issues.map { pullRequest ->
        pullRequest.toRepoPullRequestViewModel()
    }
}
