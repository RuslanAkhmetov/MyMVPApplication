package ru.geekbrain.android.mymvpapplication.domain.entities.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser (
    @PrimaryKey val id : Long,
    val login : String,
    val avatarUrl : String,
    val url: String,
    val reposUrl : String,
)