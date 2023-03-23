package ru.geekbrain.android.mymvpapplication.ui.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.databinding.FragmentUserInfoBinding
import ru.geekbrain.android.mymvpapplication.domain.entities.GithubUser
import ru.geekbrain.android.mymvpapplication.ui.AndroidScreens
import ru.geekbrain.android.mymvpapplication.ui.BackButtonListener

class UserInfoFragment(var position: Int) : MvpAppCompatFragment(), UserInfoContract.UserInfoView,
    BackButtonListener {

    private lateinit var binding: FragmentUserInfoBinding

    private val presenter by moxyPresenter {
        UserInfoPresenter(
            App.instance.gitHubUsersRepo,
            App.instance.router,
            AndroidScreens(),
            position
        )
    }


    companion object {
        fun newInstance(viewPosition: Int) = UserInfoFragment(viewPosition)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserInfoBinding.inflate(
            LayoutInflater.from(context),
            container,
            false)
        return binding.root
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }


    override fun setUserInfo(githubUser: GithubUser) {
        binding.avatarImageView.load(githubUser.avatarUrl)
        binding.loginTextView.text = githubUser.login
        binding.idTextView.text = githubUser.id.toString()
    }


}