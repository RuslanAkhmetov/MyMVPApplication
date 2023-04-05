package ru.geekbrain.android.mymvpapplication.ui.userinfo

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo

class UserInfoPresenter(
    private val usersRepo: UsersRepo,
    private val router: Router,
    private val position: Int
):
    MvpPresenter<UserInfoContract.UserInfoView>(),
    UserInfoContract.UserInfoPresenter {

    var gitHubUser: GithubUser? = null

    lateinit var disposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserInfo(position)

    }

    override fun loadUserInfo(index: Int) {


    }

    fun backPressed(): Boolean {
        router.exit() // .navigateTo(screens.users())
        disposable.dispose()
        return true
    }
}