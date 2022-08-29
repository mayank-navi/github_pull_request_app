package com.navi.github_app.data.repository

import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navi.github_app.R
import com.navi.github_app.data.api.Constants
import com.navi.github_app.data.api.github.GithubApiImpl
import com.navi.github_app.data.model.PullRequest
import com.navi.github_app.ui.HomeScreen.PullRequestAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubApiRepository {
    private var prData = ArrayList<PullRequest>()
    private lateinit var prAdapter: PullRequestAdapter

    fun getAllClosedPullRequests(
        rvPullRequest: RecyclerView,
        layoutManager: LinearLayoutManager,
        noDataTv: TextView,
        userName: Editable?,
        repoName: Editable?
    ): Unit {
        val url = Constants.BASE_URL + "/repos/$userName/$repoName/pulls"
        val closedPullRequestsCall = GithubApiImpl().getAllClosedPullRequests(url)
        closedPullRequestsCall.enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>?, response: Response<List<PullRequest>>?) {
                if(response?.body() != null)
                    noDataTv.visibility = View.GONE
                    for(pr in response?.body()!!){
                        prData.add(pr)
                    }
                    prAdapter = PullRequestAdapter(prData)
                    rvPullRequest.layoutManager = layoutManager
                    rvPullRequest.itemAnimator = DefaultItemAnimator()
                    rvPullRequest.adapter = prAdapter
            }
            override fun onFailure(call: Call<List<PullRequest>>?, t: Throwable?) {
                noDataTv.visibility = View.VISIBLE
                Log.d("RESPONSE_FAILED", call.toString())
            }
        })
    }
}
