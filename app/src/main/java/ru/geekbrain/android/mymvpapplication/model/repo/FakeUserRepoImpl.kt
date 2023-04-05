package ru.geekbrain.android.mymvpapplication.model.repo

import android.os.Handler
import android.os.Looper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser

private const val DATA_LOADING_FAKE_DELAY = 1000L

class FakeUserRepoImpl : UsersRepo {
    private val userList = listOf<GithubUser>(
        GithubUser("mojombo", 1,  "https://avatars.githubusercontent.com/u/1?v=4", ""),
        GithubUser("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4", ""),
        GithubUser("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4", ""),
        GithubUser("wycats", 4, "https://avatars.githubusercontent.com/u/4?v=4", "")
    )




    override fun getUsers(onSuccess: (List<GithubUser>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed( {
            onSuccess(userList)
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getUsersProvider(): Single<List<GithubUser>> =
        Single.just(userList).subscribeOn(Schedulers.io())



    override fun getUserRepoProvider(urlLogin: String): Single<List<GitHubRepository>> =
            Single.just(null)


}