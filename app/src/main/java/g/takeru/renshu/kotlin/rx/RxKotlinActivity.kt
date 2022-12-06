package g.takeru.renshu.kotlin.rx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import g.takeru.renshu.R
import g.takeru.renshu.databinding.ActivityMainBinding
import g.takeru.renshu.databinding.ActivityRxKotlinBinding
import io.reactivex.*
import io.reactivex.Observable.create
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toFlowable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import timber.log.Timber

class RxKotlinActivity : AppCompatActivity() {

    /**
     * ref: https://www.jianshu.com/p/785d9dfb0a5b
     */

    private lateinit var binding: ActivityRxKotlinBinding
    lateinit var disposable : Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_kotlin)
        binding = ActivityRxKotlinBinding.inflate(layoutInflater)

        /**
         *  Observable / observer
         *
         *  (not support backpressure)
         *  (not throw MissingBackpressureException and cause OOM)
         */

        // toObservable and subscribeBy (rxKotlin)
        val list = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
        list.toObservable()
                .filter{ it.length >= 5}
                .subscribeBy (
                    onNext = { println(it) },
                    onError =  { it.printStackTrace() },
                    onComplete = { println("Done!") }
                )


        // create
        val createObservable1 : Observable<String> = Observable.create (object :  ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("Hello")
                emitter.onNext("World")
            }
        })


        val createObservable2 = create (object :  ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("Hello")
                emitter.onNext("World")
            }
        })

        // create (convent to lambda)
        val createObservable3 = create (ObservableOnSubscribe<String> {
            it.onNext("Hello")
            it.onNext("World")
        })

        createObservable1
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        Timber.d("complete")
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(result: String) {
                        Timber.d(result)
                    }

                    override fun onError(throwable: Throwable) {
                        Timber.d("${throwable.message}")
                    }
                })

        //
        createObservable2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        { result ->  Timber.d(result) },
                        // onError
                        { throwable ->  Timber.d("${throwable.message}")},
                        // onComplete
                        { Timber.d("complete") },
                        // onSubscribe
                        { disposable -> Timber.d("disposable: " + disposable.isDisposed) }
                )

        // only handle onNext
        createObservable2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        { result ->  Timber.d(result) }
                )

         // only handle onNext (lambda argument should be moved out of parentheses)
        createObservable2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ result -> Timber.d(result) }


        // handle onNext and onError
        createObservable2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->  Timber.d(result) },
                        { throwable ->  Timber.d("${throwable.message}")}
                )

        // handle onNext, onError and on complete
        createObservable2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->  Timber.d(result) },
                        { throwable ->  Timber.d("${throwable.message}")},
                        { Timber.d("complete") }
                )


        createObservable3
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { Timber.d(it) }
                )

        // get disposable from observable and dispose it when you want to cancel it
        disposable = createObservable3
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { Timber.d("disposable: $it") },
                        onComplete = { Timber.d("disposable:: onComplete") }
                )
//        disposable.dispose()


        /**
         *  Flowable / Subscriber
         */
        list.toFlowable()
                .subscribe(object : Consumer<String> {
                    override fun accept(t: String?) {
                        Timber.d("Flowable: $t")
                    }
                })

        list.toFlowable()
                .subscribe(Consumer<String> { Timber.d("Flowable: $it") })

        list.toFlowable()
                .subscribe { Timber.d("Flowable: $it") }

        Flowable.range(0, 10)
                .subscribe(object : Subscriber<Int> {
                    var subscription : Subscription? = null
                    override fun onNext(t: Int?) {
                        Timber.d("onNext--->$t")
                        subscription?.request(3)
                    }

                    override fun onComplete() {
                        Timber.d("onComplete")
                    }

                    override fun onSubscribe(s: Subscription?) {
                        Timber.d("onSubscribe start")
                        subscription = s
                        subscription?.request(1)
                        Timber.d("onSubscribe end")
                    }

                    override fun onError(t: Throwable?) {
                        Timber.d("onError")
                    }

                })
    }
}


