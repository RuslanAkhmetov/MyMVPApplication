package ru.geekbrain.android.mymvpapplication.ui.userslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrain.android.mymvpapplication.databinding.RecyclerviewUserItemBinding
import ru.geekbrain.android.mymvpapplication.model.image.IImageLoader
import ru.geekbrain.android.mymvpapplication.ui.IUserListPresenter
import javax.inject.Inject

class UsersRVAdapter(private val presenter: IUserListPresenter ) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RecyclerviewUserItemBinding.inflate(
            LayoutInflater.from(parent.context),  parent, false))
            .apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int =
        presenter.getCount()

    inner class ViewHolder(private val vb: RecyclerviewUserItemBinding) :
        RecyclerView.ViewHolder(vb.root), GithubUsersContract.UserItemView {

        override var pos = -1

        override fun setLogin(text: String) {
            vb.tvLogin.text = text

        }

        override fun setUrl(text: String) {
            vb.urlLogin.text = text
        }


        override fun loadAvatar(avatarUrl: String) {
            with(vb.root){imageLoader.loadInto(avatarUrl, vb.avatarImageView)}
        }


    }


}