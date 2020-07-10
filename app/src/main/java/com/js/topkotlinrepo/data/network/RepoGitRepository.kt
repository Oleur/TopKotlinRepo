package com.js.topkotlinrepo.data.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.js.topkotlinrepo.GetAllKotlinRepositoriesQuery
import com.js.topkotlinrepo.GetIssuesQuery
import com.js.topkotlinrepo.GetPullRequestsQuery
import com.js.topkotlinrepo.GetRepositoryInsightsByDateQuery

class RepoGitRepository(private val apolloClient: ApolloClient) {

    suspend fun getTopKotlinRepositories() = apolloClient.query(GetAllKotlinRepositoriesQuery()).toDeferred().await()

    suspend fun getGitRepository(name: String, owner: String)
            = apolloClient.query(GetRepositoryInsightsByDateQuery(name, owner)).toDeferred().await()

    suspend fun getPullRequestsCreatedByDateRange(
        name: String,
        owner: String,
        startDate: String,
        endDate: String
    ): Response<GetPullRequestsQuery.Data> {
        val query = "repo:$owner/$name is:pr created:$startDate..$endDate"
        return apolloClient.query(GetPullRequestsQuery(query)).toDeferred().await()
    }

    suspend fun getIssuesCreatedByDateRange(
        name: String,
        owner: String,
        startDate: String,
        endDate: String
    ): Response<GetIssuesQuery.Data> {
        val query = "repo:$owner/$name is:issue created:$startDate..$endDate"
        return apolloClient.query(GetIssuesQuery(query)).toDeferred().await()
    }
}
