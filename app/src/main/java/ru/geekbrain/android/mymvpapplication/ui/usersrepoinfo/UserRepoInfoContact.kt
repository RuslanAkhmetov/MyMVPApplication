package ru.geekbrain.android.mymvpapplication.ui.usersrepoinfo

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

class UserRepoInfoContact {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface UserRepoInfoView : MvpView{
        fun setName(name: String)
        fun setId (id: Long)
        fun setForksCount(forksCount: Int?)
        fun setDescription (description: String)
        fun showMessage(messageText: String)
    }

    interface UserRepoInfoPresenter {
        fun loadUserRepoData(index: Int)
        fun backPressed(): Boolean
    }
}