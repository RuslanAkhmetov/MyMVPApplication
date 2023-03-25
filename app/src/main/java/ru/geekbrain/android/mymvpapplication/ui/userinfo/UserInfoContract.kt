package ru.geekbrain.android.mymvpapplication.ui.userinfo

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser

class UserInfoContract {

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface UserInfoView: MvpView {
        fun setUserInfo(githubUser: GithubUser)
    }

    interface UserInfoPresenter {
        fun loadUserInfo(index: Int)
    }

}