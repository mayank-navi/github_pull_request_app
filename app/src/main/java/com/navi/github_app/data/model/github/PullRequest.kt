package com.navi.github_app.data.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("user")
    val user: User? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("closed_at")
    val closedAt: String? = null
)

data class User(
    @SerializedName("avatar_url")
    val avatarImageUrl: String? = null,
    @SerializedName("login")
    val name: String? = null
)
