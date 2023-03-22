package ru.geekbrain.android.mymvpapplication.ui

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.domain.repos.UsersRepo

@InjectViewState
class UserPresenter(private val usersRepo: UsersRepo, val router: Router): GithubUsersContract.Presenter,
    MvpPresenter<GithubUsersContract.UserView>() {

    class UserListPresenterImpl: IUserListPresenter{
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() =
            users.size

    }


    val userListPresenter = UserListPresenterImpl()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = {itemView ->
            //TODO: переход на экран пользователя
        }
    }

    override fun loadData() {
        val users = usersRepo.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }


}