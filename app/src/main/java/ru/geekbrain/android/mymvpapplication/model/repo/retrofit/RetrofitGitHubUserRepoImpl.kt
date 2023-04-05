package ru.geekbrain.android.mymvpapplication.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.model.api.IDataSource
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo

class RetrofitGitHubUserRepoImpl(private val api: IDataSource): UsersRepo {

    override fun getUsersProvider() =
        api.getUsers()
            .subscribeOn(Schedulers.io())


    override fun getUsers(onSuccess: (List<GithubUser>) -> Unit, onError: ((Throwable) -> Unit)?) {
        TODO("Not yet implemented")
    }


    override fun getUserRepoProvider(urlLogin: String): Single<List<GitHubRepository>> =
        api.getRepositoriesList(urlLogin)
            .subscribeOn(Schedulers.io())


}