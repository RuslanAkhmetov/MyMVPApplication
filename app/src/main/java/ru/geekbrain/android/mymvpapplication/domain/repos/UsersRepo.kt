package ru.geekbrain.android.mymvpapplication.domain.repos

import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser


interface UsersRepo {
    //CRUD
    //Create(-)
    //Read

    fun getUsers(): List<GithubUser>

    fun getUser(index: Int): GithubUser

    fun getUsers(
        onSuccess: (List<GithubUser>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
    //Update(-)
    //Delete(-)



}