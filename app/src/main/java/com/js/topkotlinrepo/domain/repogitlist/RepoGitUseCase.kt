package com.js.topkotlinrepo.domain.repogitlist

import com.js.topkotlinrepo.GetAllKotlinRepositoriesQuery
import com.js.topkotlinrepo.GetIssuesQuery
import com.js.topkotlinrepo.GetPullRequestsQuery
import com.js.topkotlinrepo.data.network.RepoGitRepository
import com.js.topkotlinrepo.ui.repogit.DateRange
import org.joda.time.DateTime

class RepoGitUseCase(private val repository: RepoGitRepository) {

    suspend fun getTopKotlinRepositories(): List<RepoGit> {
        return try {
            val response = repository.getTopKotlinRepositories()
            if (response.hasErrors()) {
                emptyList()
            } else {
                mapRepoGitList(response.data?.search?.nodes)
            }
        } catch (e: Exception) {
             emptyList()
        }
    }

    suspend fun getGitRepository(name: String, owner: String): RepoGitDetail? {
        return try {
            val response = repository.getGitRepository(name, owner)
            if (response.hasErrors()) {
                null
            } else {
                response.data?.repository?.toRepoGitDetail()
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getPullRequestsCreatedByDateRange(
        name: String,
        owner: String,
        startDate: String,
        endDate: String
    ): List<RepoPullRequest> {
        return try {
            val response = repository.getPullRequestsCreatedByDateRange(name, owner, startDate, endDate)
            if (response.hasErrors()) {
                emptyList()
            } else {
                mapPullRequests(response.data?.search?.nodes)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getIssuesCreatedByDateRange(
        name: String,
        owner: String,
        startDate: String,
        endDate: String
    ): List<RepoIssue> {
        return try {
            val response = repository.getIssuesCreatedByDateRange(name, owner, startDate, endDate)
            if (response.hasErrors()) {
                emptyList()
            } else {
                mapIssues(response.data?.search?.nodes)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getDates(): List<DateRange> {
        val dates = mutableListOf<DateRange>()

        var now = DateTime()
        dates.add(DateRange(now.minusWeeks(1).toDate(), now.toDate()))

        for (i in 1..51) {
            now = now.minusWeeks(1)
            dates.add(DateRange(now.minusWeeks(1).toDate(), now.toDate()))
        }

        return dates
    }

    private fun mapIssues(issues: List<GetIssuesQuery.Node?>?) = issues?.map { node ->
        node!!.asIssue!!.toRepoIssue()
    } ?: emptyList()

    private fun mapPullRequests(nodes: List<GetPullRequestsQuery.Node?>?) = nodes?.map { node ->
        node!!.asPullRequest!!.toRepoPullRequest()
    } ?: emptyList()

    private fun mapRepoGitList(nodes: List<GetAllKotlinRepositoriesQuery.Node?>?) = nodes?.map { node ->
        node!!.asRepository!!.toRepoGit()
    } ?: emptyList()
}
