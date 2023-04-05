package ru.geekbrain.android.mymvpapplication.ui.usersrepos

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface UserRepositoriesContract {

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface UserRepositoriesView: MvpView {
        fun init()
        fun updateList()
        fun showMessage(textMessage: String)
    }

    interface UserRepositoriesPresenter{
        fun loadData()
        fun backPressed(): Boolean
    }

    interface RepositoryItemView: IItemView {
        fun setId(id: String)
        fun setName(name: String)

    }

    interface IItemView {
        var pos: Int
    }

    interface IListPresenter<V: IItemView> {
        var itemClickListener: ((V) -> Unit)?
        fun bindView(view: V)
        fun getCount(): Int
    }

    interface IRepositoriesListPresenter: IListPresenter<RepositoryItemView>
}