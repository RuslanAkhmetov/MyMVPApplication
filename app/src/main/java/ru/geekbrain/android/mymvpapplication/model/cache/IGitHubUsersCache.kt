package ru.geekbrain.android.mymvpapplication.model.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser

interface IGitHubUsersCache {
    fun getFromCache(): Single<List<GithubUser>>
    fun setToCache(users: List<GithubUser>)
}