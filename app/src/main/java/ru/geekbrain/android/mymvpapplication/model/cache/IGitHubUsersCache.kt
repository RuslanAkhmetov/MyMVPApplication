package ru.geekbrain.android.mymvpapplication.model.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser

interface IGitHubUsersCache {
    fun getUsersProviderFromCache(): Single<List<GithubUser>>
    fun setUsersToCache(users: List<GithubUser>)

    fun getUserReposProviderFromCache(login: String): Single<List<GitHubRepository>>
    fun setUserReposToCache(login: String, userRepos: List<GitHubRepository>)
}