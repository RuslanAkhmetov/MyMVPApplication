package ru.geekbrain.android.mymvpapplication.ui.usersrepos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrain.android.mymvpapplication.databinding.RecyclerviewUserRepositoryItemBinding

class UserRepositoriesRVAdapter(
    private val userRepositoriesPresenter: UserRepositoriesContract.IRepositoriesListPresenter
) :
    RecyclerView.Adapter<UserRepositoriesRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val vb: RecyclerviewUserRepositoryItemBinding) :
        RecyclerView.ViewHolder(vb.root),
        UserRepositoriesContract.RepositoryItemView {
        override fun setId(id: String) {
            vb.idTextView.text = id
        }

        override fun setName(name: String) {
            vb.nameTextView.text = name
        }

        override var pos: Int = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RecyclerviewUserRepositoryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            this.itemView.setOnClickListener {
                userRepositoriesPresenter.itemClickListener?.invoke(this)
            }
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        userRepositoriesPresenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int =
        userRepositoriesPresenter.getCount()

}