package com.example.github.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Profile(
    @Expose
    @SerializedName("bio")
    val bio: String,
    @Expose
    @SerializedName("company")
    val company: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("location")
    var location: String,
    @Expose
    @SerializedName("followers")
    val followers: Int,
    @Expose
    @SerializedName("following")
    val following: Int,
    @Expose
    @SerializedName("public_repos")
    val publicRepos: Int,
    @Expose
    @SerializedName("public_gists")
    val publicGists: Int,
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("avatar_url")
    val avatar: String
)