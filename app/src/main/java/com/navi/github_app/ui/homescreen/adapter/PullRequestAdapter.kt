package com.navi.github_app.ui.homescreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.navi.github_app.R
import com.navi.github_app.data.model.PullRequest
import com.navi.github_app.databinding.HomeScreenBinding
import com.navi.github_app.databinding.PullRequestCardBinding

class PullRequestAdapter(private val prData: ArrayList<PullRequest>) :
    RecyclerView.Adapter<PullRequestAdapter.ViewHolder>(){

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PullRequestCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pr: PullRequest = prData[position]
        holder.tvOwner.text = pr.user?.name
        holder.tvStatus.text = pr.state
        holder.tvCreateAt.text = pr.createdAt?.substring(0, 10)
        holder.tvClosedAt.text = pr.closedAt?.substring(0, 10)
        holder.tvTitle.text = pr.title
        context?.let {
            Glide.with(context!!)
                .load(pr.user?.avatarImageUrl)
                .into(holder.ivAvatar)
        }
    }

    override fun getItemCount(): Int {
        return prData.size;
    }

    class ViewHolder(private val binding: PullRequestCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvOwner: TextView = binding.cardOwnerValue
        val tvStatus: TextView = binding.cardStatusValue
        val tvCreateAt: TextView = binding.cardCreateAtValue
        val tvClosedAt: TextView = binding.cardClosedAtValue
        val tvTitle: TextView = binding.cardTitleValue
        val ivAvatar: ImageView = binding.userAvatar
    }

}