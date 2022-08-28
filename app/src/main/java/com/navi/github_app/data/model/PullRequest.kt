package com.navi.github_app.data.model

data class PullRequest(
    val title: String,
    val created_at: String,
    val closed_at: String,
    val user: String,
    val userImageUrl: String
)
