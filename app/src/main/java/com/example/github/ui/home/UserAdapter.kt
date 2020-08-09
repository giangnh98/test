package com.example.github.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.github.data.model.User
import com.example.github.data.model.UserDiffCallback
import com.example.github.databinding.UserItemLayoutBinding
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class UserAdapter @AssistedInject constructor(
    @Assisted private val listener: UserAdapterListener,
    private val requestManager: RequestManager
): ListAdapter<User, UserViewHolder>(UserDiffCallback) {

    interface UserAdapterListener {
        fun onItemClicked(view: View, user: User)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(listener: UserAdapterListener): UserAdapter
    }

    override fun submitList(list: MutableList<User>?) {
        super.submitList(ArrayList<User>(list ?: listOf()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            requestManager,
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

}