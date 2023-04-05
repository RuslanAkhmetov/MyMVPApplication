package ru.geekbrain.android.mymvpapplication.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}