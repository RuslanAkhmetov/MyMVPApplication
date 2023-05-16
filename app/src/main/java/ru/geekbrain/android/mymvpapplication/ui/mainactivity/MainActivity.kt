package ru.geekbrain.android.mymvpapplication.ui.mainactivity

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.R
import ru.geekbrain.android.mymvpapplication.databinding.ActivityMainBinding
import ru.geekbrain.android.mymvpapplication.ui.BackButtonListener
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainActivityContract.MainView {

    @Inject
    lateinit var navigatorHolder : NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }

    }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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