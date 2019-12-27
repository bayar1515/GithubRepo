package com.githubrepo.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class RepoModel :Serializable {
    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("owner")
    @Expose
    private var owner: Owner? = null
    @SerializedName("stargazers_count")
    @Expose
    private var stargazersCount: Int? = null
    @SerializedName("open_issues_count")
    @Expose
    private var openIssuesCount: Int? = null


    fun getId(): Int? {
        return id
    }

    fun setId(id:Int){
        this.id = id
    }


    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }



    fun getOwner(): Owner? {
        return owner
    }

    fun setOwner(owner: Owner?) {
        this.owner = owner
    }

    fun getStargazersCount(): Int? {
        return stargazersCount
    }

    fun setStargazersCount(stargazersCount: Int?) {
        this.stargazersCount = stargazersCount
    }

    fun getOpenIssuesCount(): Int? {
        return openIssuesCount
    }

    fun setOpenIssuesCount(openIssuesCount: Int?) {
        this.openIssuesCount = openIssuesCount
    }


    class Owner :Serializable{
        @SerializedName("login")
        @Expose
        private var login: String? = null
        @SerializedName("id")
        @Expose
        private var id: Int? = null

        @SerializedName("avatar_url")
        @Expose
        private var avatarUrl: String? = null


        fun getLogin(): String? {
            return login
        }

        fun setLogin(login: String?) {
            this.login = login
        }

        fun getId(): Int? {
            return id
        }

        fun setId(id: Int?) {
            this.id = id
        }

        fun getAvatarUrl(): String? {
            return avatarUrl
        }

        fun setAvatarUrl(avatarUrl: String?) {
            this.avatarUrl = avatarUrl
        }
    }

}