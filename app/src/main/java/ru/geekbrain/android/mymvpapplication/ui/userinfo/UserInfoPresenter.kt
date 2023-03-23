package ru.geekbrain.android.mymvpapplication.ui.userinfo

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.domain.repos.UsersRepo
import ru.geekbrain.android.mymvpapplication.ui.IScreens

class UserInfoPresenter(
    private val usersRepo: UsersRepo,
    private val router: Router,
    private val screens: IScreens,
    private val position: Int
):
    MvpPresenter<UserInfoContract.UserInfoView>(),
    UserInfoContract.UserInfoPresenter {

    var gitHubUser: GithubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        gitHubUser=loadUserInfo(position).apply {
            viewState.setUserInfo(this)
        }
    }

    override fun loadUserInfo(index: Int): GithubUser =
        usersRepo.getUser(index)



    fun backPressed(): Boolean {
        router.navigateTo(screens.users())
        return true
    }
}