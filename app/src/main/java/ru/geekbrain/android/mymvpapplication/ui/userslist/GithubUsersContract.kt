package ru.geekbrain.android.mymvpapplication.ui.userslist

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


class GithubUsersContract {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface UserView: MvpView {
        fun init()
        fun updateList()
        fun release()
    }

    interface Presenter{
        fun loadData()
        fun backPressed(): Boolean
    }

    interface UserItemView: IItemView {
        fun setLogin(text: String)
        fun loadAvatar(avatarUrl: String)
    }

    interface IItemView {
        var pos: Int
    }
}