package ru.geekbrain.android.mymvpapplication.ui.network

import android.content.Context
import android.database.Observable
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.geekbrain.android.mymvpapplication.model.network.INetworkStatus

class AndroidNetworkStatus(context:Context): INetworkStatus {

    private val statusSubject: BehaviorSubject<Boolean> =BehaviorSubject.create()

    init{
        statusSubject.onNext(false)

       val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
               as ConnectivityManager

       val request = NetworkRequest.Builder().build()

       connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback(){
           override fun onAvailable(network: Network) {
                statusSubject.onNext(true)
           }

           override fun onUnavailable() {
               statusSubject.onNext(false)
           }

           override fun onLost(network: Network) {
               statusSubject.onNext(false)
           }
       })
    }

    override fun isOnline()= statusSubject as Observable<Boolean>

    override fun isOnlineSingle()= statusSubject.first(false)
}