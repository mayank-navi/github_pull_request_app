package com.navi.github_app.data.repository

import com.navi.github_app.data.api.Constants
import com.navi.github_app.data.api.github.GithubImpl
import com.navi.github_app.data.model.PullRequest
import retrofit2.Call

class GithubRepository {
    fun getAllClosedPullRequests(
        userName: String?,
        repoName: String?
    ): Call<ArrayList<PullRequest>> {
        val url = Constants.BASE_URL + "/repos/$userName/$repoName/pulls"
        return GithubImpl().getAllClosedPullRequests(url)
    }
}
