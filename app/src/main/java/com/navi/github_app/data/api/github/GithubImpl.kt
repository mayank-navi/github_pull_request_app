package com.navi.github_app.data.api.github

import com.navi.github_app.data.api.RetroFitClient
import com.navi.github_app.data.model.PullRequest
import retrofit2.Call

class GithubImpl: GithubService {
    override fun getAllClosedPullRequests(owner: String, repo: String, state: String, token: String): Call<ArrayList<PullRequest>> {
        val githubApiInterface: GithubService = RetroFitClient().getClient().create(
            GithubService::class.java)
        return githubApiInterface.getAllClosedPullRequests(owner, repo, state = state)
    }
}