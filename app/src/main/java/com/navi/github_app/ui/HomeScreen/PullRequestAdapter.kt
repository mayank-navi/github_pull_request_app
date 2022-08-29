package com.navi.github_app.ui.HomeScreen

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

class PullRequestAdapter (private val prData: ArrayList<PullRequest>) :
    RecyclerView.Adapter<PullRequestAdapter.ViewHolder>(){

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.pull_request_card, parent, false)
        context = parent.context
        return ViewHolder(cardView)
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvOwner: TextView
        val tvStatus: TextView
        val tvCreateAt: TextView
        val tvClosedAt: TextView
        val tvTitle: TextView
        val ivAvatar: ImageView

        init {
            tvOwner = view.findViewById(R.id.card_owner_value)
            tvStatus = view.findViewById(R.id.card_status_value)
            tvCreateAt = view.findViewById(R.id.card_create_at_value)
            tvClosedAt = view.findViewById(R.id.card_closed_at_value)
            tvTitle = view.findViewById(R.id.card_title_value)
            ivAvatar = view.findViewById(R.id.user_avatar)
        }
    }

}