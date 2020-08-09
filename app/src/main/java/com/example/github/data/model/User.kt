package com.example.github.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("avatar_url")
    val avatar: String,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("node_id")
    val nodeId: String
)

object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.login == newItem.login
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}
