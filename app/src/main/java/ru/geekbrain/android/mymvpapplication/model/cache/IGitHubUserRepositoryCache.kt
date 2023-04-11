package ru.geekbrain.android.mymvpapplication.model.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository

interface IGitHubUserRepositoryCache {

    fun getUserReposProviderFromCache(login: String): Single<List<GitHubRepository>>
    fun setUserReposToCache(login: String, userRepos: List<GitHubRepository>)
}