package ru.geekbrain.android.mymvpapplication.di.modules.repository

import dagger.Subcomponent
import ru.geekbrain.android.mymvpapplication.di.modules.repository.module.RepositoryModule
import ru.geekbrain.android.mymvpapplication.ui.usersrepoinfo.UserRepoInfoPresenter
import ru.geekbrain.android.mymvpapplication.ui.usersrepos.UserRepositoriesListPresenter

@RepositoryScope
@Subcomponent(
    modules = [RepositoryModule::class]
)
interface RepositorySubComponent {

    fun inject(userRepositoriesListPresenter: UserRepositoriesListPresenter)
    fun inject(userRepoInfoPresenter: UserRepoInfoPresenter)

}