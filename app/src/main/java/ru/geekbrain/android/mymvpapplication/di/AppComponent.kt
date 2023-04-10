package ru.geekbrain.android.mymvpapplication.di

import dagger.Component
import ru.geekbrain.android.mymvpapplication.di.modules.*
import ru.geekbrain.android.mymvpapplication.ui.mainactivity.MainActivity
import ru.geekbrain.android.mymvpapplication.ui.mainactivity.MainPresenter
import ru.geekbrain.android.mymvpapplication.ui.userslist.UsersListPresenter
import ru.geekbrain.android.mymvpapplication.ui.usersrepoinfo.UserRepoInfoPresenter
import ru.geekbrain.android.mymvpapplication.ui.usersrepos.UserRepositoriesListPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class,
        AppModule::class,
        CicerroneModule::class,
        DateBaseModule::class,
        RepoModule::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersListPresenter: UsersListPresenter)
    fun inject(usersRepositoriesListPresenter: UserRepositoriesListPresenter)
    fun inject(usersRepoInfoPresenter: UserRepoInfoPresenter)

}