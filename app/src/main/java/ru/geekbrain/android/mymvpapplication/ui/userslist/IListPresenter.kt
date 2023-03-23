package ru.geekbrain.android.mymvpapplication.ui

import ru.geekbrain.android.mymvpapplication.ui.userslist.GithubUsersContract

interface IListPresenter<V: GithubUsersContract.IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter: IListPresenter<GithubUsersContract.UserItemView>