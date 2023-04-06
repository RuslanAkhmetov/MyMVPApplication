package ru.geekbrain.android.mymvpapplication.ui.usersrepoinfo

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.model.repo.UsersRepo

class UserRepoInfoPresenter(
    private val mainThread: Scheduler,
    private val router: Router,
    private val userRepo: UsersRepo,
    private val userLogin: String,
    private val repoIndex: Int
): MvpPresenter<UserRepoInfoContact.UserRepoInfoView>(),
        UserRepoInfoContact.UserRepoInfoPresenter{

    private val TAG = "UserRepoInfoPresenter"

    private var gitHubUserRepo : GitHubRepository? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserRepoData(repoIndex)
    }

    override fun loadUserRepoData(index: Int) {
        userRepo.getUserRepoProvider(userLogin)
            .observeOn(mainThread)
            .subscribe({list ->
                gitHubUserRepo = list[index]
                Log.i(TAG, "loadUserRepoData: ${Thread.currentThread()}")
                gitHubUserRepo?.let { userRepo ->
                    viewState.setName(userRepo.name)
                    viewState.setId(userRepo.id)
                    userRepo.forksCount?.let {  viewState.setForksCount(it)}
                    userRepo.description?.let {  viewState.setDescription(it)}
                }
            },{
                viewState.showMessage("Error: ${it.message}")
            })
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }


}