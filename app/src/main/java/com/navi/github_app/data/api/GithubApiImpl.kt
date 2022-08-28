package com.navi.github_app.data.api

import android.util.Log
import com.navi.github_app.data.model.PullRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubApiImpl: GithubApiService {
    override fun getAllClosedPullRequests(token: String, state: String): Call<List<PullRequest>> {
        val githubApiInterface: GithubApiService = RetroFitClient().getClient().create(GithubApiService::class.java)
        val closedPullRequestsCall: Call<List<PullRequest>> = githubApiInterface.getAllClosedPullRequests()
        return closedPullRequestsCall
    }
}