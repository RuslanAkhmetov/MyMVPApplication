package ru.geekbrain.android.mymvpapplication.domain.entities.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.geekbrain.android.mymvpapplication.domain.entities.room.dao.RepositoryDao
import ru.geekbrain.android.mymvpapplication.domain.entities.room.dao.UserDao

@androidx.room.Database(
    entities = [RoomGitHubUser::class, RoomGitHubRepository::class],
    version = 1,
    exportSchema= false
)
abstract class DataBase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: DataBase? = null

        fun getInstance() = instance ?: throw RuntimeException("DB was not created!")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, DataBase::class.java, DB_NAME)
                    .build()
            }
        }
    }
}