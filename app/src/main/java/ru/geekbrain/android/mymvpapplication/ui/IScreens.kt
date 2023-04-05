package ru.geekbrain.android.mymvpapplication.ui

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users() : Screen

    fun userInfo(viewPosition: Int): Screen

    fun userRepos(login: String): Screen

    fun userReposInfo(viewPosition: Int):Screen
}