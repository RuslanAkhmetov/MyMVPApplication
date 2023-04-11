package ru.geekbrain.android.mymvpapplication.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrain.android.mymvpapplication.model.api.IDataSource
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo

class RetrofitGitHubUsersRepoImpl(
    private val api: IDataSource

) : UsersRepo {

     override fun getUsersProvider() =
         api.getUsers()
             .subscribeOn(Schedulers.io())



}