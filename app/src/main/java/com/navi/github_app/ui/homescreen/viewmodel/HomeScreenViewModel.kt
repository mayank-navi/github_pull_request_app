package com.navi.github_app.ui.homescreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navi.github_app.data.model.PullRequest
import com.navi.github_app.data.repository.GithubRepository
import com.navi.github_app.ui.homescreen.viewmodel.response.PullRequestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class HomeScreenViewModel(): ViewModel() {
    val prLiveData = MutableLiveData<PullRequestResponse>()

    fun fetchPrData(githubRepository: GithubRepository, userName: String, repoName: String) {
        val prCall : Call<ArrayList<PullRequest>> = githubRepository.getAllClosedPullRequests(userName, repoName)
        prCall.enqueue(object : Callback<ArrayList<PullRequest>> {
                override fun onResponse(call: Call<ArrayList<PullRequest>>?, response: Response<ArrayList<PullRequest>>?) {
                    if(response?.code() != 404) {
                        val prData = response?.body()
                        val prResponse = prData?.let { PullRequestResponse(false, it) }
                        prLiveData.postValue(prResponse)

                    }
                    else {
                        val prResponse = PullRequestResponse(true, null)
                        prLiveData.postValue(prResponse)
                    }
                }
                override fun onFailure(call: Call<ArrayList<PullRequest>>?, t: Throwable?) {
                    val prResponse = PullRequestResponse(true, null)
                    prLiveData.postValue(prResponse)
                }
            })
    }
}