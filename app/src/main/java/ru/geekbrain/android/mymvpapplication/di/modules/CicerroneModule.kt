package ru.geekbrain.android.mymvpapplication.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.geekbrain.android.mymvpapplication.ui.AndroidScreens
import ru.geekbrain.android.mymvpapplication.ui.IScreens
import javax.inject.Singleton

@Module
class CicerroneModule {
    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun screens(): IScreens =
        AndroidScreens

    @Provides
    fun cicerone() : Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

}