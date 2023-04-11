package ru.geekbrain.android.mymvpapplication.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository

interface UserRepositoryRepo {
    fun getUserRepoProvider(urlLogin: String): Single<List<GitHubRepository>>
}