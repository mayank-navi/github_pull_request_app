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
    fun getAllClosedPullRequests(
        rvPullRequest: RecyclerView,
        noDataTv: TextView,
        userName: String?,
        repoName: String?
    ): Unit {
        val url = Constants.BASE_URL + "/repos/$userName/$repoName/pulls"
        val closedPullRequestsCall = GithubApiImpl().getAllClosedPullRequests(url)
        closedPullRequestsCall.enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>?, response: Response<List<PullRequest>>?) {
                if(response?.code() != 404) {
                    val prDataList = response?.body() as ArrayList<PullRequest>
                    rvPullRequest.adapter = PullRequestAdapter(prDataList)
                    rvPullRequest.visibility = View.VISIBLE;
                    noDataTv.visibility = View.GONE
                }
                else {
                    rvPullRequest.visibility = View.GONE;
                    noDataTv.visibility = View.VISIBLE
                }
            }
            override fun onFailure(call: Call<List<PullRequest>>?, t: Throwable?) {
                noDataTv.visibility = View.VISIBLE
                rvPullRequest.visibility = View.GONE;
                Log.d("RESPONSE_FAILED", call.toString())
            }
        })
    }
}
