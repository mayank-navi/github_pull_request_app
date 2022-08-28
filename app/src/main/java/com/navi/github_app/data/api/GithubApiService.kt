package com.navi.github_app.data.api

import com.navi.github_app.data.model.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GithubApiService {
    @GET(Constants.PULL_REQUESTS)
    fun getAllClosedPullRequests(
        @Header("Authorization")
        token: String = Constants.GITHUB_ACCESS_TOKEN,
        @Query("state") state: String = "closed"
    ): Call<List<PullRequest>>
}