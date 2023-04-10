package ru.geekbrain.android.mymvpapplication.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrain.android.mymvpapplication.model.api.IDataSource
import ru.geekbrain.android.mymvpapplication.model.cache.IGitHubUsersCache
import ru.geekbrain.android.mymvpapplication.model.network.INetworkStatus
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo
import ru.geekbrain.android.mymvpapplication.model.repo.combine.CombineGitHubUserRepoImpl
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun userRepo(api: IDataSource,
                 networkStatus: INetworkStatus,
                 cache: IGitHubUsersCache): UsersRepo =
        CombineGitHubUserRepoImpl(api, networkStatus, cache)



}