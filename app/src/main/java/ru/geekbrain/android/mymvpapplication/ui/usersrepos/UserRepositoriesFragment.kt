package ru.geekbrain.android.mymvpapplication.ui.usersrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.databinding.FragmentUserRepositoriesBinding
import ru.geekbrain.android.mymvpapplication.ui.AndroidScreens

class UserRepositoriesFragment(userLogin: String): MvpAppCompatFragment(),
    UserRepositoriesContract.UserRepositoriesView {

    companion object{
        fun newInstance(userLogin: String) =
            UserRepositoriesFragment(userLogin)
    }

    private val userRepositoriesPresenter : UserRepositoriesListPresenter by moxyPresenter {
        UserRepositoriesListPresenter(
            AndroidSchedulers.mainThread(),
            App.instance.gitHubUsersRepo,
            App.instance.router,
            userLogin,
            AndroidScreens
        )
    }

    private var binding: FragmentUserRepositoriesBinding? = null

    private var userRepositoriesAdapter: UserRepositoriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRepositoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun init() {
        binding?.rvUserRepositories?.layoutManager = LinearLayoutManager(context)
        userRepositoriesAdapter =
            UserRepositoriesRVAdapter(userRepositoriesPresenter.userRepositoriesListPresenter)
        binding?.rvUserRepositories?.adapter = userRepositoriesAdapter
    }

    override fun updateList() {
        userRepositoriesAdapter?.notifyDataSetChanged()
    }

    override fun showMessage(textMessage: String) {
        Toast.makeText(activity, textMessage, Toast.LENGTH_LONG).show()
    }
}