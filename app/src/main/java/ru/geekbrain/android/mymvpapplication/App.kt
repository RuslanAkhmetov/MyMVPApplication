package ru.geekbrain.android.mymvpapplication

import android.app.Application
import android.content.Context
import ru.geekbrain.android.mymvpapplication.di.AppComponent
import ru.geekbrain.android.mymvpapplication.di.DaggerAppComponent
import ru.geekbrain.android.mymvpapplication.di.modules.AppModule
import ru.geekbrain.android.mymvpapplication.di.modules.repository.RepositorySubComponent
import ru.geekbrain.android.mymvpapplication.di.modules.user.UserSubComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    var userSubComponent: UserSubComponent? = null

    var repositorySubComponent: RepositorySubComponent? = null


    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }

    fun initUserSubComponent() = appComponent.userSubComponent().also {
        userSubComponent = it
    }

    fun releaseUserSubComponent() {
        userSubComponent = null
    }

    fun initRepositorySubComponent() = userSubComponent?.repositorySubComponent().also {
        repositorySubComponent = it
    }

    fun releaseRepositorySubComponent() {
        repositorySubComponent = null
    }


}

val Context.app: App
    get() {
        return applicationContext as App
    }