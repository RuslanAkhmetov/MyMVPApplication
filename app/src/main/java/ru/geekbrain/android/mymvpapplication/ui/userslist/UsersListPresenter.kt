package ru.geekbrain.android.mymvpapplication.ui.userslist

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo
import ru.geekbrain.android.mymvpapplication.ui.IScreens
import ru.geekbrain.android.mymvpapplication.ui.IUserListPresenter
import ru.geekbrain.android.mymvpapplication.ui.userslist.GithubUsersContract.UserItemView
import javax.inject.Inject


class UsersListPresenter(private var mainThreadScheduler: Scheduler,
    private val screens: IScreens
) : MvpPresenter<GithubUsersContract.UserView>(),
    GithubUsersContract.Presenter  {


    @Inject
    lateinit var usersRepo: UsersRepo
    @Inject
    lateinit var router: Router

    private val TAG = "UsersListPresenter"

    class UserListPresenterImpl : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)?=null


        override fun bindView(view: UserItemView) {
            val user = users[view.pos]

            user?.let {
                view.setLogin(it.login)
                view.loadAvatar(it.avatarUrl) }
        }

        override fun getCount() =
            users.size

    }


    val userListPresenter = UserListPresenterImpl()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = { itemView ->

            router.navigateTo(screens.userRepos(userListPresenter.users[itemView.pos].login))
        }
    }


    override fun loadData() {

        usersRepo.getUsersProvider()
            .observeOn(mainThreadScheduler)
            .subscribe({ gitHubUser ->
                userListPresenter.users.clear()
                userListPresenter.users.addAll(gitHubUser)
                viewState.updateList()
            },
                {
                    Log.i(TAG, "loadData: ")
                    it.printStackTrace()

                    println("Error ${it.message}")
                })

    }

    override fun backPressed(): Boolean {
        router.exit()

        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }

}