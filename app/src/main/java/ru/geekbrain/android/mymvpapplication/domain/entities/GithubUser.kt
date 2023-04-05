package ru.geekbrain.android.mymvpapplication.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @Expose val login : String,
    @Expose val id : Long,
    //val node_id : String,
    @Expose
    val avatarUrl : String,
    /*val gravatar_id : String,
    val url : String,
    val html_url : String,
    val followers_url : String,
    val following_url : String,
    val gists_url : String,
    val starred_url : String,
    val subscriptions_url : String,
    val organizations_url : String,*/
    @Expose
    val reposUrl : String,
    /*val events_url : String,
    val received_events_url : String,
    val type : String,
    val site_admin : Boolean*/
) : Parcelable
