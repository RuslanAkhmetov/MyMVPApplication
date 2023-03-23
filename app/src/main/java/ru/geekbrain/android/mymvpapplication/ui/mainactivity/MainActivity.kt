package ru.geekbrain.android.mymvpapplication.ui.mainactivity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.R
import ru.geekbrain.android.mymvpapplication.app
import ru.geekbrain.android.mymvpapplication.databinding.ActivityMainBinding
import ru.geekbrain.android.mymvpapplication.ui.AndroidScreens
import ru.geekbrain.android.mymvpapplication.ui.BackButtonListener
import ru.geekbrain.android.mymvpapplication.ui.userslist.UsersRVAdapter


class MainActivity : MvpAppCompatActivity(), MainActivityContract.MainView {

    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(app.router, AndroidScreens())
    }

    private var adapter: UsersRVAdapter? = null

    private lateinit var binding: ActivityMainBinding

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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is BackButtonListener && fragment.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }


}