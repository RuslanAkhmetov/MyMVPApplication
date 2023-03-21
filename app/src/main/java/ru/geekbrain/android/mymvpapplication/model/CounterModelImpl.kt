package ru.geekbrain.android.mymvpapplication.model

class CounterModelImpl: CounterModel {

    val counters = mutableListOf(0, 0, 0)
    override fun set(index: Int, value: Int) {
        counters[index-1] = value
    }

    override fun getCurrent(index: Int): Int =
            counters[index-1]


    override fun next(index: Int): Int =
        ++counters[index-1]

}