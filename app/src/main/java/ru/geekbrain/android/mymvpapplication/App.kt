package ru.geekbrain.android.mymvpapplication

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.geekbrain.android.mymvpapplication.domain.entities.room.DataBase
import ru.geekbrain.android.mymvpapplication.model.cache.room.RoomGitHubUserCacheImpl
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo
import ru.geekbrain.android.mymvpapplication.model.repo.combine.CombineGitHubUserRepoImpl
import ru.geekbrain.android.mymvpapplication.ui.network.AndroidNetworkStatus

class App: Application() {

    companion object{
        lateinit var  instance: App
    }

    val gitHubUsersRepo: UsersRepo by lazy {
        CombineGitHubUserRepoImpl(
            ApiHolder.api,
            AndroidNetworkStatus(instance),
            RoomGitHubUserCacheImpl(DataBase.getInstance())
        )
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        DataBase.create(this)
    }


}

val Context.app: App
    get() {
       return applicationContext as App
    }