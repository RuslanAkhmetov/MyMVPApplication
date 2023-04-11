package ru.geekbrain.android.mymvpapplication.ui.usersrepos

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.model.repo.UserRepositoryRepo
import ru.geekbrain.android.mymvpapplication.ui.IScreens
import javax.inject.Inject

class UserRepositoriesListPresenter(
    private val mainThread: Scheduler,

    val login: String,
    private val screens: IScreens
): MvpPresenter<UserRepositoriesContract.UserRepositoriesView>(),
    UserRepositoriesContract.UserRepositoriesPresenter {

    @Inject
    lateinit var userRepositoryRepo: UserRepositoryRepo
    @Inject
    lateinit var router: Router

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
        userRepositoryRepo.getUserRepoProvider(login)
            .observeOn(mainThread)
            .subscribe({list->
                userRepositoriesListPresenter.userRepositoriesList.clear()
                userRepositoriesListPresenter.userRepositoriesList.addAll(list)
                viewState.updateList()
            },
                {
                    viewState.showMessage("Error: ${it.message}")
                    it.printStackTrace()
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