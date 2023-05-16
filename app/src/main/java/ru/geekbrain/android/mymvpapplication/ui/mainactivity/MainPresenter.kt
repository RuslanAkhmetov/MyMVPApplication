package ru.geekbrain.android.mymvpapplication.ui.mainactivity

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.ui.IScreens
import javax.inject.Inject

class MainPresenter() :
    MvpPresenter<MainActivityContract.MainView>() {

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var router:Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }

}

