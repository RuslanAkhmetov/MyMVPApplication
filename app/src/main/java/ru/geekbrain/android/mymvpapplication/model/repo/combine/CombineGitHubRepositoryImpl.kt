package ru.geekbrain.android.mymvpapplication.model.repo.combine

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.model.api.IDataSource
import ru.geekbrain.android.mymvpapplication.model.cache.IGitHubUserRepositoryCache
import ru.geekbrain.android.mymvpapplication.model.network.INetworkStatus
import ru.geekbrain.android.mymvpapplication.model.repo.UserRepositoryRepo

class CombineGitHubRepositoryImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val userRepositoryCache: IGitHubUserRepositoryCache,
): UserRepositoryRepo {

    override fun getUserRepoProvider(
        urlLogin: String
    ): Single<List<GitHubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getRepositoriesList(urlLogin).flatMap { userReposList ->
                    userRepositoryCache.setUserReposToCache(urlLogin, userReposList)
                    Single.fromCallable {userReposList}
                }
            } else {
                userRepositoryCache.getUserReposProviderFromCache(urlLogin)
            }
        }.subscribeOn(Schedulers.io())

}