package ru.geekbrain.android.mymvpapplication.ui

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


class GithubUsersContract {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface UserView: MvpView {
        fun init()
        fun updateList()
    }

    interface Presenter{
        fun loadData()
    }
}