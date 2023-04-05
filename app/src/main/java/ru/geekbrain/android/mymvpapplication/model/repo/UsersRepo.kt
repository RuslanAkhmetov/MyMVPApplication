package ru.geekbrain.android.mymvpapplication.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser


interface UsersRepo {
    //CRUD
    //Create(-)
    //Read

    //fun getUsers(): List<GithubUser>

    fun getUsersProvider(): Single<List<GithubUser>>

    //fun getUser(index: Int): GithubUser

    fun getUserRepoProvider(urlLogin: String): Single<List<GitHubRepository>>

    fun getUsers(
        onSuccess: (List<GithubUser>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
    //Update(-)
    //Delete(-)



}