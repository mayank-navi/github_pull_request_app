package com.navi.github_app.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
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
import com.navi.github_app.databinding.HomeScreenBinding
import com.navi.github_app.ui.HomeScreen.PullRequestAdapter

class MainActivity : AppCompatActivity() {
    private var prData = ArrayList<PullRequest>()
    private lateinit var prAdapter: PullRequestAdapter
    private lateinit var binding: HomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        val homeView = binding.root
        setContentView(homeView)
        setupUI()
    }

    @SuppressLint("CutPasteId")
    private fun setupPullRequestRV() {
        val rvPullRequest: RecyclerView = binding.rvPullRequests
        val layoutManager = LinearLayoutManager(applicationContext)
        rvPullRequest.layoutManager = layoutManager
        rvPullRequest.itemAnimator = DefaultItemAnimator()
        prAdapter = PullRequestAdapter(prData)
        rvPullRequest.adapter = prAdapter
        rvPullRequest.visibility = View.GONE
    }

    private fun setupUI () {
        setupPullRequestRV()
        binding.fetchButton.setOnClickListener {
            val repoName = binding.repoNameInput.text.toString()
            val userName = binding.userNameInput.text.toString()
            onFetchPress(userName, repoName)
        }
    }

    private fun onFetchPress(userName: String?, repoName: String?) {
        val noDataTv: TextView = binding.tvErrorWhenNoUserInputs
        val prRv: RecyclerView = binding.rvPullRequests
        GithubApiRepository().getAllClosedPullRequests(prRv, noDataTv, userName, repoName)
    }
}