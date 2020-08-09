package com.example.github.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repo(
    @Expose
    @SerializedName("name")
    val name: String
)