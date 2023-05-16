package ru.geekbrain.android.mymvpapplication.ui.usersrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.databinding.FragmentUserRepositoriesBinding
import ru.geekbrain.android.mymvpapplication.di.modules.repository.RepositorySubComponent
import ru.geekbrain.android.mymvpapplication.ui.AndroidScreens
import ru.geekbrain.android.mymvpapplication.ui.BackButtonListener

const val USER_LOGIN = "userLogin"

class UserRepositoriesFragment : MvpAppCompatFragment(),
    UserRepositoriesContract.UserRepositoriesView, BackButtonListener {


    var userLogin: String = ""


    var repositorySubComponent: RepositorySubComponent? = null

    companion object {
        fun newInstance(userLogin: String)= UserRepositoriesFragment().apply {
            arguments = Bundle().apply {
                putString(USER_LOGIN, userLogin)
            }
            this.userLogin = userLogin
            repositorySubComponent = App.instance.initRepositorySubComponent()
            /*App.instance.appComponent.inject(this)*/
        }
    }

    private val userRepositoriesPresenter: UserRepositoriesListPresenter by moxyPresenter {
        UserRepositoriesListPresenter(
            userLogin,
            AndroidScreens
        ).apply {
            repositorySubComponent?.inject(this)
        }
    }

    private var binding: FragmentUserRepositoriesBinding? = null

    private var userRepositoriesAdapter: UserRepositoriesRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userLogin = it.getString(USER_LOGIN).toString()
        }
    }

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

    override fun release() {
        repositorySubComponent =null
        App.instance.releaseRepositorySubComponent()
    }

    override fun showMessage(textMessage: String) {
        Toast.makeText(activity, textMessage, Toast.LENGTH_LONG).show()
    }

    override fun backPressed(): Boolean {
        userRepositoriesPresenter.backPressed()
        return true
    }
}