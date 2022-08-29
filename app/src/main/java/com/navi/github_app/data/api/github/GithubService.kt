package com.navi.github_app.data.api.github

import com.navi.github_app.data.api.Constants
import com.navi.github_app.data.model.PullRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface GithubService {
    @GET
    fun getAllClosedPullRequests(
        @Url url: String,
        @Query("state") state: String = "closed",
        @Header("Authorization")
        token: String = Constants.GITHUB_ACCESS_TOKEN,
    ): Call<ArrayList<PullRequest>>
}