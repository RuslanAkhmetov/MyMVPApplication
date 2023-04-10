package ru.geekbrain.android.mymvpapplication.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.domain.entities.room.DataBase
import ru.geekbrain.android.mymvpapplication.model.cache.IGitHubUsersCache
import ru.geekbrain.android.mymvpapplication.model.cache.room.RoomGitHubUserCacheImpl
import javax.inject.Singleton

@Module
class DateBaseModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, DataBase::class.java, DataBase.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun usersCache(dataBase: DataBase): IGitHubUsersCache{
        return RoomGitHubUserCacheImpl(dataBase)
    }
}