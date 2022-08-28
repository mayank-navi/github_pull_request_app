package com.navi.github_app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.navi.github_app.R
import com.navi.github_app.data.api.GithubApiImpl
import com.navi.github_app.data.model.PullRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val closedPullRequestsCall = GithubApiImpl().getAllClosedPullRequests()
        closedPullRequestsCall.enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(
                call: Call<List<PullRequest>>?,
                response: Response<List<PullRequest>>?
            ) {
                Log.d("RESPONSE", response.toString())
            }

            override fun onFailure(call: Call<List<PullRequest>>?, t: Throwable?) {
                Log.d("RESPONSE", call.toString())
            }
        })
    }
}