package ru.geekbrain.android.mymvpapplication.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrain.android.mymvpapplication.ui.userinfo.UserInfoFragment
import ru.geekbrain.android.mymvpapplication.ui.userslist.UsersListFragment

class AndroidScreens: IScreens {
    override fun users(): Screen = FragmentScreen{ UsersListFragment.newInstance()}

    override fun userInfo(viewPosition: Int): Screen  =
        FragmentScreen { UserInfoFragment.newInstance(viewPosition)}

}