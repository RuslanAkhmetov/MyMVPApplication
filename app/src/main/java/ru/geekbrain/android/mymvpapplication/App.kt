package ru.geekbrain.android.mymvpapplication

import android.app.Application
import android.content.Context
import ru.geekbrain.android.mymvpapplication.model.CounterModelImpl

class App: Application() {
    val model = CounterModelImpl()
}

val Context.app: App
    get() {
       return applicationContext as App
    }