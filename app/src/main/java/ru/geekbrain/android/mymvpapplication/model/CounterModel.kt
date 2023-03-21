package ru.geekbrain.android.mymvpapplication.model

interface CounterModel {
    //CRUD
    //Create
    fun set(index:Int, value:Int)
    //Read
    fun getCurrent(index: Int): Int
    //Update
    fun next(index: Int):Int

}