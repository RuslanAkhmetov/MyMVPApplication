package ru.geekbrain.android.mymvpapplication.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users(): Screen = FragmentScreen{ UserFragment.newInstance()}

}