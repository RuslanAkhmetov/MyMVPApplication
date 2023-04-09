package ru.geekbrain.android.mymvpapplication.model.repo.combine

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.domain.entities.room.DataBase
import ru.geekbrain.android.mymvpapplication.model.api.IDataSource
import ru.geekbrain.android.mymvpapplication.model.cache.room.RoomGitHubUserCacheImpl
import ru.geekbrain.android.mymvpapplication.model.network.INetworkStatus
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo

class CombineGitHubUserRepoImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: DataBase,
) : UsersRepo {

    override fun getUsersProvider(): Single<List<GithubUser>> = networkStatus
        .isOnlineSingle()
        .flatMap<List<GithubUser>?> { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { usersList ->
                    RoomGitHubUserCacheImpl(db).setUsersToCache(usersList)
                    Single.fromCallable { usersList }
                }

            } else {
                RoomGitHubUserCacheImpl(db).getUsersProviderFromCache()
            }
        }.subscribeOn(Schedulers.io())


    override fun getUserRepoProvider(
        urlLogin: String
    ): Single<List<GitHubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getRepositoriesList(urlLogin).flatMap { userReposList ->
                    RoomGitHubUserCacheImpl(db).setUserReposToCache(urlLogin, userReposList)
                    Single.fromCallable {userReposList}
                }
            } else {
                RoomGitHubUserCacheImpl(db).getUserReposProviderFromCache(urlLogin)
            }
        }.subscribeOn(Schedulers.io())

}