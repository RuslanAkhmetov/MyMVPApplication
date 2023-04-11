package ru.geekbrain.android.mymvpapplication.di.modules.user

import dagger.Subcomponent
import ru.geekbrain.android.mymvpapplication.di.modules.repository.RepositorySubComponent
import ru.geekbrain.android.mymvpapplication.di.modules.user.module.UserModule
import ru.geekbrain.android.mymvpapplication.ui.userslist.UsersListPresenter
import ru.geekbrain.android.mymvpapplication.ui.userslist.UsersRVAdapter

@UserScope
@Subcomponent(
    modules = [UserModule::class]
)
interface UserSubComponent {
    fun repositorySubComponent(): RepositorySubComponent

    fun inject(usersListPresenter: UsersListPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}