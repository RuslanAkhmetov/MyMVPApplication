package ru.geekbrain.android.mymvpapplication.domain


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class Creation {

    fun exec(){
        Consumer(Producer()).exec()
    }
}

class Consumer(val producer: Producer) {
    val stringObserver = object : Observer<String> {
        var disposable: Disposable? = null

        override fun onSubscribe(d: Disposable) {
            disposable = d
            println("OnSubscribe")
        }

        override fun onNext(t: String) {
            println("OnNext $t")
        }

        override fun onError(e: Throwable) {
            println("onError: ${e.message}")
        }

        override fun onComplete() {
            println("OnComplete")
        }


    }

    fun exec(){
        execJust()
    }

    fun execJust(){
        producer.just().subscribe(stringObserver)
    }

}

class Producer {
    fun just(): Observable<String> {
        return Observable.just("1", "2", "3")
    }



}


