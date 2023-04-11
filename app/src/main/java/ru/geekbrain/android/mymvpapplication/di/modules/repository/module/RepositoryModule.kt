package ru.geekbrain.android.mymvpapplication.di.modules.repository.module

import dagger.Module
import dagger.Provides
import ru.geekbrain.android.mymvpapplication.di.modules.repository.RepositoryScope
import ru.geekbrain.android.mymvpapplication.domain.entities.room.DataBase
import ru.geekbrain.android.mymvpapplication.model.api.IDataSource
import ru.geekbrain.android.mymvpapplication.model.cache.IGitHubUserRepositoryCache
import ru.geekbrain.android.mymvpapplication.model.cache.room.RoomGitHubUserRepositoryCacheImpl
import ru.geekbrain.android.mymvpapplication.model.network.INetworkStatus
import ru.geekbrain.android.mymvpapplication.model.repo.UserRepositoryRepo
import ru.geekbrain.android.mymvpapplication.model.repo.combine.CombineGitHubRepositoryImpl

@Module
class RepositoryModule {

    @Provides
    fun userRepositoryRepo(api: IDataSource,
                           networkStatus: INetworkStatus,
                           userRepositoryCache: IGitHubUserRepositoryCache
    ): UserRepositoryRepo =
        CombineGitHubRepositoryImpl(api, networkStatus, userRepositoryCache)

    @RepositoryScope
    @Provides
    fun userRepositoryCache(dataBase: DataBase): IGitHubUserRepositoryCache {
        return RoomGitHubUserRepositoryCacheImpl(dataBase)
    }
}