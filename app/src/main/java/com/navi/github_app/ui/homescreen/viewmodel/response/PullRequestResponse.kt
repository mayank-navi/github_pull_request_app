package com.navi.github_app.ui.homescreen.viewmodel.response

import com.navi.github_app.data.model.PullRequest
import java.util.*
import kotlin.collections.ArrayList

data class PullRequestResponse(val isError: Boolean, val data: ArrayList<PullRequest>?) {
}