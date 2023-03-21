package ru.geekbrain.android.mymvpapplication.ui

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


class CounterContract {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface MainView: MvpView {
        fun setCounterOneText(counterText: String)
        fun setCounterTwoText(counterText: String)
        fun setCounterThreeText(counterText: String)
    }

    interface Presenter{
        fun counterOneClick()
        fun counterTwoClick()
        fun counterThreeClick()
    }
}