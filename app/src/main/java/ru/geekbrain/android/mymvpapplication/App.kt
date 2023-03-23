package ru.geekbrain.android.mymvpapplication

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.geekbrain.android.mymvpapplication.data.FakeUserRepoImpl
import ru.geekbrain.android.mymvpapplication.domain.repos.UsersRepo

class App: Application() {

    companion object{
        lateinit var  instance: App
    }

    val gitHubUsersRepo: UsersRepo = FakeUserRepoImpl()

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}

val Context.app: App
    get() {
       return applicationContext as App
    }