package ru.geekbrain.android.mymvpapplication.ui.usersrepoinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.databinding.FragmentUserRepoInfoBinding

const val USER_LOGIN = "userLogin"
const val INDEX_OF_REPO = "indexOfRepo"

class UserRepoInfoFragment: MvpAppCompatFragment(), UserRepoInfoContact.UserRepoInfoView {

    var userLogin: String = ""
    var indexOfRepo: Int = -1

    val userRepoInfoPresenter : UserRepoInfoPresenter by moxyPresenter {
        UserRepoInfoPresenter(
            AndroidSchedulers.mainThread(),
            App.instance.router,
            App.instance.gitHubUsersRepo,
            userLogin,
            indexOfRepo
        )
    }

    lateinit var binding: FragmentUserRepoInfoBinding

    companion object{
        fun newInstance(login: String, indexRepo: Int): UserRepoInfoFragment{
            val args = Bundle()
            args.putString(USER_LOGIN, login)
            args.putInt(INDEX_OF_REPO, indexRepo)
            val fragment = UserRepoInfoFragment()
            fragment.arguments = args
            fragment.userLogin = login
            fragment.indexOfRepo = indexRepo
            return fragment
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