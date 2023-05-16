package ru.geekbrain.android.mymvpapplication.di.modules.user.module

import dagger.Module
import dagger.Provides
import ru.geekbrain.android.mymvpapplication.di.modules.user.UserScope
import ru.geekbrain.android.mymvpapplication.domain.entities.room.DataBase
import ru.geekbrain.android.mymvpapplication.model.api.IDataSource
import ru.geekbrain.android.mymvpapplication.model.cache.IGitHubUsersCache
import ru.geekbrain.android.mymvpapplication.model.cache.room.RoomGitHubUsersCacheImpl
import ru.geekbrain.android.mymvpapplication.model.network.INetworkStatus
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo
import ru.geekbrain.android.mymvpapplication.model.repo.combine.CombineGitHubUserRepoImpl

@Module
class UserModule {

    @UserScope
    @Provides
    fun userRepo(api: IDataSource,
                 networkStatus: INetworkStatus,
                 cache: IGitHubUsersCache
    ): UsersRepo =
        CombineGitHubUserRepoImpl(api, networkStatus, cache)



    @Provides
    fun usersCache(dataBase: DataBase): IGitHubUsersCache{
        return RoomGitHubUsersCacheImpl(dataBase)
    }
}