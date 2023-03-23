package ru.geekbrain.android.mymvpapplication.ui.userslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.databinding.FragmentUsersBinding
import ru.geekbrain.android.mymvpapplication.ui.AndroidScreens
import ru.geekbrain.android.mymvpapplication.ui.BackButtonListener

class UsersListFragment: MvpAppCompatFragment(), GithubUsersContract.UserView, BackButtonListener {

    companion object{
        fun newInstance() = UsersListFragment()
    }

    val presenter: UsersListPresenter by moxyPresenter {
        UsersListPresenter(App.instance.gitHubUsersRepo,  App.instance.router, AndroidScreens())
    }

    var adapter: UsersRVAdapter?= null

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
        adapter = UsersRVAdapter(presenter = presenter.userListPresenter)
        binding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean =
        presenter.backPressed()



}