package ru.geekbrain.android.mymvpapplication.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser

interface IDataSource {


    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}/{repos}")
    fun getRepositoriesList(@Path(value="login") login: String, @Path("repos") repos: String = "repos"): Single<List<GitHubRepository>>
}