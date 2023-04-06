package ru.geekbrain.android.mymvpapplication.ui

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users() : Screen

    fun userRepos(login: String): Screen

    fun userRepoInfo(login: String, viewPosition: Int):Screen
}