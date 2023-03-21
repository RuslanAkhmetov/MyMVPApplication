package ru.geekbrain.android.mymvpapplication.ui



class CounterContract {
    interface MainView {
        fun setCounterText(counterIndex: Int, counterText: String)
    }

    interface Presenter{
        fun onAttach(view: MainView)
        fun onAction (indexOfActionMaker : Int)
        fun onRestoreCounter(index: Int):Int
    }
}