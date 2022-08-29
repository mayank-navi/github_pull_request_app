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
        val pr: PullRequest = prData.get(position)
        holder.tv_owner.text = pr.user?.name
        holder.tv_status.text = pr.state
        holder.tv_create_at.text = pr.createdAt?.substring(0, 10)
        holder.tv_closed_at.text = pr.closedAt?.substring(0, 10)
        holder.tv_title.text = pr.title
        context?.let {
            Glide.with(context!!)
                .load(pr.user?.avatarImageUrl)
                .into(holder.iv_avatar)
        }
    }

    override fun getItemCount(): Int {
        return prData.size;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_owner: TextView
        val tv_status: TextView
        val tv_create_at: TextView
        val tv_closed_at: TextView
        val tv_title: TextView
        val iv_avatar: ImageView
        init {
            tv_owner = view.findViewById(R.id.card_owner_value)
            tv_status = view.findViewById(R.id.card_status_value)
            tv_create_at = view.findViewById(R.id.card_create_at_value)
            tv_closed_at = view.findViewById(R.id.card_closed_at_value)
            tv_title = view.findViewById(R.id.card_title_value)
            iv_avatar = view.findViewById(R.id.user_avatar)
        }
    }

}