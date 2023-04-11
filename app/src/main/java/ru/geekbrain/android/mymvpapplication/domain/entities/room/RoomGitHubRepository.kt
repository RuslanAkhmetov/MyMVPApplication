package ru.geekbrain.android.mymvpapplication.domain.entities.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]

)
data class RoomGitHubRepository(
    @PrimaryKey val id: Long,
    val name: String,
    val forksCount: Int? = null,
    val description: String,

    val userId: Long
)