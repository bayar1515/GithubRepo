package com.githubrepo.network

import com.githubrepo.model.RepoModel
import retrofit2.http.GET
import retrofit2.http.Path


interface IServices {
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String?) :retrofit2.Call<ArrayList<RepoModel>>
}