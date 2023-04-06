package ru.geekbrain.android.mymvpapplication.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrain.android.mymvpapplication.ui.userslist.UsersListFragment
import ru.geekbrain.android.mymvpapplication.ui.usersrepoinfo.UserRepoInfoFragment
import ru.geekbrain.android.mymvpapplication.ui.usersrepos.UserRepositoriesFragment

object AndroidScreens: IScreens {
    override fun users(): Screen = FragmentScreen{ UsersListFragment.newInstance()}

    override fun userRepos(login: String): Screen =
        FragmentScreen{ UserRepositoriesFragment.newInstance(login)}

    override fun userRepoInfo(login:  String, viewPosition: Int): Screen =
        FragmentScreen{UserRepoInfoFragment.newInstance(login, viewPosition)}

}