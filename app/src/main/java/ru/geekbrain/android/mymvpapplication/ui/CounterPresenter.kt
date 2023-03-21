package ru.geekbrain.android.mymvpapplication.ui

import ru.geekbrain.android.mymvpapplication.model.CounterModelImpl

class CounterPresenter(var model: CounterModelImpl): CounterContract.Presenter {
    private lateinit var view: CounterContract.MainView

    override fun onAttach(view: CounterContract.MainView) {
        this.view = view
    }


    override fun onAction(indexOfActionMaker: Int) {
        view.setCounterText(indexOfActionMaker, model.next(indexOfActionMaker).toString())
    }

    override fun onRestoreCounter(index: Int): Int =
        model.getCurrent(index)

}