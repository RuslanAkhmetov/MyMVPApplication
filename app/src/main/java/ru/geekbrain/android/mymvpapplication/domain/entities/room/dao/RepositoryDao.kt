package ru.geekbrain.android.mymvpapplication.domain.entities.room.dao

import androidx.room.*
import ru.geekbrain.android.mymvpapplication.domain.entities.room.RoomGitHubRepository

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGitHubRepository)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGitHubRepository)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGitHubRepository>)

    @Update
    fun update(user: RoomGitHubRepository)
    @Update
    fun update(vararg users: RoomGitHubRepository)
    @Update
    fun update(users: List<RoomGitHubRepository>)

    @Delete
    fun delete(user: RoomGitHubRepository)
    @Delete
    fun delete(vararg users: RoomGitHubRepository)
    @Delete
    fun delete(users: List<RoomGitHubRepository>)

    @Query("SELECT * FROM RoomGitHubRepository")
    fun getAll(): List<RoomGitHubRepository>

    @Query("SELECT * FROM RoomGitHubRepository WHERE userId = :userId")
    fun findById(userId: Long): List<RoomGitHubRepository>


}