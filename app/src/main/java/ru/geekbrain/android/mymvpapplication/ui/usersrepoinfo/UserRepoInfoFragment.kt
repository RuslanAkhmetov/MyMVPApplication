package ru.geekbrain.android.mymvpapplication.ui.usersrepoinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.databinding.FragmentUserRepoInfoBinding
import ru.geekbrain.android.mymvpapplication.di.modules.repository.RepositorySubComponent

const val USER_LOGIN = "userLogin"
const val INDEX_OF_REPO = "indexOfRepo"

class UserRepoInfoFragment: MvpAppCompatFragment(), UserRepoInfoContact.UserRepoInfoView {

    var userLogin: String = ""
    var indexOfRepo: Int = -1

    var repositorySubComponent: RepositorySubComponent? = null

    val userRepoInfoPresenter : UserRepoInfoPresenter by moxyPresenter {
        repositorySubComponent = App.instance.initRepositorySubComponent()

        UserRepoInfoPresenter(
            userLogin,
            indexOfRepo
        ).apply {
            repositorySubComponent?.inject(this)
        }
    }

    lateinit var binding: FragmentUserRepoInfoBinding

    companion object{
        fun newInstance(login: String, indexRepo: Int)= UserRepoInfoFragment().apply{
            arguments = Bundle().apply {
                putString(USER_LOGIN, login)
                putInt(INDEX_OF_REPO, indexRepo)
            }

            userLogin = login
            indexOfRepo = indexRepo
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userLogin = it.getString(USER_LOGIN).toString()
            indexOfRepo = it.getInt(INDEX_OF_REPO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRepoInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setName(name: String) {
        binding.nameTextView.text = name
    }

    override fun setId(id: Long) {
        binding.idTextView.text = id.toString()
    }

    override fun setForksCount(forksCount: Int?) {
        binding.forksCountTextView.text=forksCount.toString()
    }

    override fun setDescription(description: String) {
        binding.descriptionTextView.text = description
    }

    override fun showMessage(messageText: String) {
        Toast.makeText(requireActivity(), messageText, Toast.LENGTH_LONG).show()
    }


}