package ru.geekbrain.android.mymvpapplication.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GitHubRepository(
    @Expose val id : Long,
    @Expose val name : String,
    @Expose val forksCount : Int? = null,
) : Parcelable

