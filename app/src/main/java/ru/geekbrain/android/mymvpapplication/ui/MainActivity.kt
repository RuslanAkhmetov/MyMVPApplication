package ru.geekbrain.android.mymvpapplication.ui

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.R
import ru.geekbrain.android.mymvpapplication.databinding.ActivityMainBinding


class MainActivity : MvpAppCompatActivity(), MainActivityContract.MainView {

    private val TAG = "MainActivity"

    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens()) }

    private var adapter: UsersRVAdapter? = null

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is BackButtonListener && fragment.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }


}