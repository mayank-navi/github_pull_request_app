package com.navi.github_app.data.api.github

import com.navi.github_app.data.api.Constants
import com.navi.github_app.data.api.RetroFitClient
import com.navi.github_app.data.model.PullRequest
import retrofit2.Call

class GithubApiImpl: GithubApiService {
    override fun getAllClosedPullRequests(url: String, state: String, token: String): Call<List<PullRequest>> {
        val githubApiInterface: GithubApiService = RetroFitClient().getClient().create(
            GithubApiService::class.java)
        return githubApiInterface.getAllClosedPullRequests(url)
    }
}