package com.navi.github_app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navi.github_app.R
import com.navi.github_app.data.model.PullRequest
import com.navi.github_app.data.repository.GithubRepository
import com.navi.github_app.databinding.FragmentFilterBottomSheetFragmentBinding
import com.navi.github_app.databinding.HomeScreenBinding
import com.navi.github_app.ui.homescreen.adapter.PullRequestAdapter
import com.navi.github_app.ui.homescreen.viewmodel.HomeScreenViewModel

class MainActivity : AppCompatActivity() {
    private var prData = ArrayList<PullRequest>()
    private lateinit var prAdapter: PullRequestAdapter
    private lateinit var binding: HomeScreenBinding
    private val homeScreenVM : HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        val homeView = binding.root
        setContentView(homeView)
        setupUI()
        initObservers()
    }

    private fun initObservers() {
        homeScreenVM.prLiveData.observe(this) {
            it?.let {
                val noDataTv: TextView = binding.tvErrorWhenNoUserInputs
                val prRv: RecyclerView = binding.rvPullRequests
                if (!it.isError && it.data != null) {
                    prRv.adapter = PullRequestAdapter(it.data)
                    prRv.visibility = View.VISIBLE;
                    noDataTv.visibility = View.GONE
                } else {
                    prRv.visibility = View.GONE;
                    noDataTv.visibility = View.VISIBLE
                }
            }
        }
    }

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
            onFetchPress()
        }
        binding.filterButton.setOnClickListener{
            FilterBottomSheetFragment().show(supportFragmentManager,null)
        }
    }

    private fun onFetchPress() {
        val repoName = binding.repoNameInput.text.toString()
        val userName = binding.userNameInput.text.toString()
        homeScreenVM.fetchPrData(GithubRepository(), userName, repoName);
    }
}