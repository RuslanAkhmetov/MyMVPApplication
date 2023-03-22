package ru.geekbrain.android.mymvpapplication.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.data.FakeUserRepoImpl
import ru.geekbrain.android.mymvpapplication.databinding.ActivityMainBinding


class MainActivity : MvpAppCompatActivity(), GithubUsersContract.UserView {

    private val TAG = "MainActivity"

    private val presenter by moxyPresenter { MainPresenter(FakeUserRepoImpl()) }
    private var adapter: UsersRVAdapter? = null

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter = presenter.userListPresenter)
        binding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


}