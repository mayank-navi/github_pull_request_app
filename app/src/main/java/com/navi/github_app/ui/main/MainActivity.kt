package com.navi.github_app.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.navi.github_app.R
import com.navi.github_app.data.model.PullRequest
import com.navi.github_app.data.repository.GithubApiRepository
import com.navi.github_app.ui.HomeScreen.PullRequestAdapter

class MainActivity : AppCompatActivity() {
    private var prData = ArrayList<PullRequest>()
    private lateinit var prAdapter: PullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        setupUI()
    }

    @SuppressLint("CutPasteId")
    private fun setupPullRequestRV() {
        val rvPullRequest: RecyclerView = findViewById(R.id.rv_pull_requests)
        val layoutManager = LinearLayoutManager(applicationContext)
        rvPullRequest.layoutManager = layoutManager
        rvPullRequest.itemAnimator = DefaultItemAnimator()
        prAdapter = PullRequestAdapter(prData)
        rvPullRequest.adapter = prAdapter
        findViewById<RecyclerView>(R.id.rv_pull_requests).visibility = View.GONE
    }

    private fun setupUI () {
        setupPullRequestRV()
        findViewById<Button>(R.id.fetch_button).setOnClickListener {
            val repoName = findViewById<TextInputEditText>(R.id.repo_name_input).text.toString()
            val userName = findViewById<TextInputEditText>(R.id.user_name_input).text.toString()
            onFetchPress(userName, repoName)
        }
    }

    private fun onFetchPress(userName: String?, repoName: String?) {
        val noDataTv: TextView = findViewById(R.id.tv_error_when_no_user_inputs)
        val prRv: RecyclerView = findViewById(R.id.rv_pull_requests)
        GithubApiRepository().getAllClosedPullRequests(prRv, noDataTv, userName, repoName)
    }
}