package ru.geekbrain.android.mymvpapplication.model.cache.room

import io.reactivex.rxjava3.core.Single
import ru.geekbrain.android.mymvpapplication.domain.entities.GitHubRepository
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.domain.entities.room.DataBase
import ru.geekbrain.android.mymvpapplication.domain.entities.room.RoomGitHubRepository
import ru.geekbrain.android.mymvpapplication.domain.entities.room.RoomGitHubUser
import ru.geekbrain.android.mymvpapplication.model.cache.IGitHubUsersCache

class RoomGitHubUserCacheImpl(private val db: DataBase) : IGitHubUsersCache {
    override fun getUsersProviderFromCache(): Single<List<GithubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomGitHubUser ->
                GithubUser(
                    roomGitHubUser.login ?: "",
                    roomGitHubUser.id ?: 0,
                    roomGitHubUser.avatarUrl ?: "",
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
                githubUser.reposUrl
            )
        }
        db.userDao.insert(roomGitHubUser)
    }

    override fun getUserReposProviderFromCache(login: String): Single<List<GitHubRepository>> {
        val userId = db.userDao.findByLogin(login).id
        return Single.fromCallable {
            db.repositoryDao.findById(userId).map { roomGitHubRepository ->
                GitHubRepository(
                    roomGitHubRepository.id ?: 0,
                    roomGitHubRepository.name ?: "",
                    roomGitHubRepository.forksCount ?: 0,
                    roomGitHubRepository.description ?: ""
                )
            }
        }
    }

    override fun setUserReposToCache(login: String, userRepos: List<GitHubRepository>) {
        val userId = db.userDao.findByLogin(login).id
        val roomUserRepos =
            userRepos.map { gitHubRepository ->
                RoomGitHubRepository(
                    gitHubRepository.id,
                    gitHubRepository.name,
                    gitHubRepository.forksCount?:0,
                    gitHubRepository.description?:"",
                    userId
                )
            }

        db.repositoryDao.insert(roomUserRepos)


    }

}