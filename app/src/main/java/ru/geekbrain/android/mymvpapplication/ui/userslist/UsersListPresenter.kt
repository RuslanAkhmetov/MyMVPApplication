package ru.geekbrain.android.mymvpapplication.ui.userslist

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.domain.repos.UsersRepo
import ru.geekbrain.android.mymvpapplication.ui.IScreens
import ru.geekbrain.android.mymvpapplication.ui.IUserListPresenter
import ru.geekbrain.android.mymvpapplication.ui.userslist.GithubUsersContract.UserItemView

@InjectViewState
class UsersListPresenter(
    private val usersRepo: UsersRepo,
    val router: Router,
    val screens: IScreens
) :
    GithubUsersContract.Presenter,
    MvpPresenter<GithubUsersContract.UserView>() {

    class UserListPresenterImpl : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() =
            users.size

    }

    lateinit var disposable: Disposable

    val userListPresenter = UserListPresenterImpl()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.userInfo(itemView.pos))
        }
    }

    fun error(error: String) {

    }

    override fun loadData() {
        disposable = Observable.just(usersRepo.getUsers())
            .subscribe(
                { gitHubUser ->
                    userListPresenter.users.addAll(gitHubUser)
                }, {
                    error(it.message.toString())
                })

        viewState.updateList()
    }

    override fun backPressed(): Boolean {
        router.exit()
        disposable.dispose()
        return true
    }


}