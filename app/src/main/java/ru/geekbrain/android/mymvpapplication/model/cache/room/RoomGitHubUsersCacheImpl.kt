package ru.geekbrain.android.mymvpapplication.model.cache.room

import io.reactivex.rxjava3.core.Single
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.domain.entities.room.DataBase
import ru.geekbrain.android.mymvpapplication.domain.entities.room.RoomGitHubUser
import ru.geekbrain.android.mymvpapplication.model.cache.IGitHubUsersCache

class RoomGitHubUsersCacheImpl(private val db: DataBase) : IGitHubUsersCache {
    override fun getUsersProviderFromCache(): Single<List<GithubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomGitHubUser ->
                GithubUser(
                    roomGitHubUser.login ?: "",
                    roomGitHubUser.id ?: 0,
                    roomGitHubUser.avatarUrl ?: "",
                    roomGitHubUser.url?:"",
                    roomGitHubUser.reposUrl ?: ""

                )
            }
        }


    override fun setUsersToCache(users: List<GithubUser>) {
        val roomGitHubUser = users.map { githubUser ->
            RoomGitHubUser(
                githubUser.id,
                githubUser.login,
                githubUser.avatarUrl,
                githubUser.url,
                githubUser.reposUrl
            )
        }
        db.userDao.insert(roomGitHubUser)
    }



}