package ru.geekbrain.android.mymvpapplication.domain.entities.room

import androidx.room.RoomDatabase
import ru.geekbrain.android.mymvpapplication.domain.entities.room.dao.RepositoryDao
import ru.geekbrain.android.mymvpapplication.domain.entities.room.dao.UserDao

@androidx.room.Database(
    entities = [RoomGitHubUser::class, RoomGitHubRepository::class],
    version = 1

)
abstract class DataBase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"

    }
}