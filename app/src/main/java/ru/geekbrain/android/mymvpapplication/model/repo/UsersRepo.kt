package ru.geekbrain.android.mymvpapplication.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser


interface UsersRepo {

    fun getUsersProvider(): Single<List<GithubUser>>

}