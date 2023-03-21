package ru.geekbrain.android.mymvpapplication.ui

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrain.android.mymvpapplication.model.CounterModelImpl

@InjectViewState
class CounterPresenter(private val model: CounterModelImpl): CounterContract.Presenter,
    MvpPresenter<CounterContract.MainView>() {


    override fun counterOneClick() {
        viewState.setCounterOneText( model.next(1).toString())
    }

    override fun counterTwoClick() {
        viewState.setCounterTwoText( model.next(2).toString())
    }

    override fun counterThreeClick() {
        viewState.setCounterThreeText( model.next(3).toString())
    }

}