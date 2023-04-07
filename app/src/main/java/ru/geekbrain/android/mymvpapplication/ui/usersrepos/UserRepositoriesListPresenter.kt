package ru.geekbrain.android.mymvpapplication.ui.usersrepos

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo
import ru.geekbrain.android.mymvpapplication.ui.IScreens

class UserRepositoriesListPresenter(
    private val mainThread: Scheduler,
    private val userRepo: UsersRepo,
    private val router: Router,
    val login: String,
    private val screens: IScreens
): MvpPresenter<UserRepositoriesContract.UserRepositoriesView>(),
    UserRepositoriesContract.UserRepositoriesPresenter {

    class  UserRepositoriesListPresenter:
    UserRepositoriesContract.IRepositoriesListPresenter{

        var userRepositoriesList = mutableListOf<GitHubRepository>()

        override var itemClickListener: ((UserRepositoriesContract.RepositoryItemView) -> Unit)? = null

        override fun bindView(view: UserRepositoriesContract.RepositoryItemView) {
            userRepositoriesList[view.pos]?.let {
                view.setName(it.name)
                view.setId(it.id.toString())
            }
        }

        override fun getCount(): Int =
            userRepositoriesList.size

    }

    val userRepositoriesListPresenter = UserRepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        userRepositoriesListPresenter.itemClickListener = {
            router.navigateTo(screens.userRepoInfo(login, it.pos))
        }

    }

    override fun loadData() {
        userRepo.getUserRepoProvider(login)
            .observeOn(mainThread)
            .subscribe({list->
                userRepositoriesListPresenter.userRepositoriesList.clear()
                userRepositoriesListPresenter.userRepositoriesList.addAll(list)
                viewState.updateList()
            },
                {
                    viewState.showMessage("Error: ${it.message}")
                })
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }
}