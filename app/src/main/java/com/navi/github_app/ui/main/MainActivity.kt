package com.navi.github_app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.navi.github_app.R
import com.navi.github_app.data.repository.GithubApiRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        findViewById<Button>(R.id.fetch_button).setOnClickListener({
            val repoName = findViewById<TextInputEditText>(R.id.repo_name_input).text
            val userName = findViewById<TextInputEditText>(R.id.user_name_input).text
            onFetch(userName, repoName)
        })
    }

    fun onFetch(userName: Editable?, repoName: Editable?) {
        val rvPullRequest: RecyclerView = findViewById(R.id.rv_pull_requests)
        val noDataTv: TextView = findViewById(R.id.tv_error_when_no_user_inputs)
        val layoutManager = LinearLayoutManager(applicationContext)
        GithubApiRepository().getAllClosedPullRequests(rvPullRequest, layoutManager, noDataTv, userName, repoName)
    }
}