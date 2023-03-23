package ru.geekbrain.android.mymvpapplication.ui.mainactivity

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.ui.IScreens

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainActivityContract.MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }

}

