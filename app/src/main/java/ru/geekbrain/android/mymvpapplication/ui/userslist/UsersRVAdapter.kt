package ru.geekbrain.android.mymvpapplication.ui.userslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrain.android.mymvpapplication.databinding.RecyclerviewItemBinding
import ru.geekbrain.android.mymvpapplication.ui.IUserListPresenter

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int =
        presenter.getCount()

    inner class ViewHolder(val vb: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(vb.root), GithubUsersContract.UserItemView {

        override var pos = -1

        override fun setLogin(text: String) {
            vb.tvLogin.text = text
        }

    }


}