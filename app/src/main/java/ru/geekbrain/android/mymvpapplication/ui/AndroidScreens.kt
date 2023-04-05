package ru.geekbrain.android.mymvpapplication.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrain.android.mymvpapplication.ui.userinfo.UserInfoFragment
import ru.geekbrain.android.mymvpapplication.ui.userslist.UsersListFragment
import ru.geekbrain.android.mymvpapplication.ui.usersrepos.UserRepositoriesFragment

object AndroidScreens: IScreens {
    override fun users(): Screen = FragmentScreen{ UsersListFragment.newInstance()}

    override fun userInfo(viewPosition: Int): Screen  =
        FragmentScreen { UserInfoFragment.newInstance(viewPosition)}

    override fun userRepos(login: String): Screen =
        FragmentScreen{ UserRepositoriesFragment.newInstance(login)}

    override fun userReposInfo(viewPosition: Int): Screen {
        TODO("Not yet implemented")
    }

}