package ru.geekbrain.android.mymvpapplication.di

import dagger.Component
import ru.geekbrain.android.mymvpapplication.di.modules.*
import ru.geekbrain.android.mymvpapplication.di.modules.user.UserSubComponent
import ru.geekbrain.android.mymvpapplication.ui.mainactivity.MainActivity
import ru.geekbrain.android.mymvpapplication.ui.mainactivity.MainPresenter
import ru.geekbrain.android.mymvpapplication.ui.userslist.UsersRVAdapter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class,
        AppModule::class,
        CicerroneModule::class,
        DateBaseModule::class,
        ImageModule::class]
)
interface AppComponent {

    fun userSubComponent(): UserSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)


}