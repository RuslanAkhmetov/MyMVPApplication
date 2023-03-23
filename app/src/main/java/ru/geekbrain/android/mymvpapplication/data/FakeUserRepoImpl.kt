package ru.geekbrain.android.mymvpapplication.data

import android.os.Handler
import android.os.Looper
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.domain.repos.UsersRepo

private const val DATA_LOADING_FAKE_DELAY = 1000L

class FakeUserRepoImpl : UsersRepo {
    private val userList = listOf<GithubUser>(
        GithubUser("mojombo", 1,  "https://avatars.githubusercontent.com/u/1?v=4"),
        GithubUser("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        GithubUser("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
        GithubUser("wycats", 4, "https://avatars.githubusercontent.com/u/4?v=4")
    )

    override fun getUsers(): List<GithubUser> =
        userList



    override fun getUsers(onSuccess: (List<GithubUser>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed( {
            onSuccess(userList)
        }, DATA_LOADING_FAKE_DELAY)

    }

    override fun getUser(index: Int): GithubUser =
        userList[index]

}