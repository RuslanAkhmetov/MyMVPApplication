package ru.geekbrain.android.mymvpapplication.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.model.api.IDataSource
import ru.geekbrain.android.mymvpapplication.model.repo.UserRepositoryRepo

class RetrofitGitHubUserRepositoryRepoImpl(
    private val api: IDataSource

) : UserRepositoryRepo {


    override fun getUserRepoProvider(urlLogin: String): Single<List<GitHubRepository>> =
        api.getRepositoriesList(urlLogin)
            .subscribeOn(Schedulers.io())


}