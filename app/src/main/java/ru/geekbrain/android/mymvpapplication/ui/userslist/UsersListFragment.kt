package ru.geekbrain.android.mymvpapplication.ui.userslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.R
import ru.geekbrain.android.mymvpapplication.databinding.FragmentUsersBinding
import ru.geekbrain.android.mymvpapplication.di.modules.user.UserSubComponent
import ru.geekbrain.android.mymvpapplication.ui.BackButtonListener

class UsersListFragment: MvpAppCompatFragment(), GithubUsersContract.UserView, BackButtonListener {


    companion object{
        fun newInstance() = UsersListFragment()
    }

    private var userSubComponent: UserSubComponent? = null

    private val presenter: UsersListPresenter by moxyPresenter {
        userSubComponent = App.instance.initUserSubComponent()

        UsersListPresenter().apply {
                userSubComponent?.inject(this)
        }
    }

    private var adapter: UsersRVAdapter?= null

    private var binding: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding?.rvUsers?.addItemDecoration(dividerItemDecoration)
        adapter = UsersRVAdapter(presenter = presenter.userListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        binding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {

        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        userSubComponent = null
        App.instance.releaseUserSubComponent()
    }

    override fun backPressed(): Boolean =
        presenter.backPressed()


}