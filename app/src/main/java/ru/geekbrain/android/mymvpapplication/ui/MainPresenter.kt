package ru.geekbrain.android.mymvpapplication.ui

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.domain.repos.UsersRepo

@InjectViewState
class MainPresenter(private val usersRepo: UsersRepo): GithubUsersContract.Presenter,
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


}