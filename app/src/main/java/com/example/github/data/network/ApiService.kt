package com.example.github.data.network

import com.example.github.data.model.Profile
import com.example.github.data.model.Repo
import com.example.github.data.model.User
import com.example.github.utils.AppConstants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users?client_id=${AppConstants.GITHUB_CLIENT_ID}&client_secret=${AppConstants.GITHUB_CLIENT_SECRET}")
    fun getUsers(): Observable<List<User>>

    @GET("/users/{login}?client_id=${AppConstants.GITHUB_CLIENT_ID}&client_secret=${AppConstants.GITHUB_CLIENT_SECRET}")
    fun getUserProfile(@Path("login") login: String): Observable<Profile>

    @GET("/users/{login}/repos?per_page=5&sort=created:asc&client_id=${AppConstants.GITHUB_CLIENT_ID}&client_secret=${AppConstants.GITHUB_CLIENT_SECRET}")
    fun getUserRepos(@Path("login") login: String): Observable<List<Repo>>
}