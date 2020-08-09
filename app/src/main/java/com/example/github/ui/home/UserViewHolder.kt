package com.example.github.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.github.data.model.User
import com.example.github.databinding.UserItemLayoutBinding

class UserViewHolder(
    private val requestManager: RequestManager,
    private val binding: UserItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, listener: UserAdapter.UserAdapterListener) {
        val (login, avatar, type, nodeId) = user
        val title = "$type - $nodeId"
        binding.run {
            senderTextView.text = title
            subjectTextView.text = login
            cardView.setOnClickListener { listener.onItemClicked(it, user) }
            requestManager
                .load(avatar)
                .circleCrop()
                .into(senderProfileImageView)
        }
    }

}