package g.takeru.renshu.rx;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import g.takeru.renshu.R;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

public class RxJava2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // basic
        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        Observable<List<String>> listObservable = Observable.just(stringList);
        listObservable.subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Timber.d("listObservable: onSubscribe");
            }

            @Override
            public void onNext(List<String> strings) {
                Timber.d("listObservable: onNext");
                Timber.d("list size: " + stringList.size());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Timber.d("listObservable: onComplete");
            }
        });

        // async
        Observable<List<String>> asyncObservable = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return stringList;
            }
        });

        asyncObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.d("asyncObservable: onSubscribe");
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        Timber.d("asyncObservable: onNext");
                        Timber.d("list size: " + stringList.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Timber.d("asyncObservable: onComplete");
                    }
                });

        // async (create)
        // note:
        // ObservableOnSubscribe is used in Observable.create() method and will do
        // that cancelation check for your inside of it
        Observable<List<String>> createObservable = Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(stringList);
//                emitter.onError(new IndexOutOfBoundsException());
            }
        });

        createObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        Timber.d("createObservable: onNext");
                        Timber.d("list size: " + stringList.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("createObservable: onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        // consumer
        // note:
        // if your observable using create and have onError, Consumer had a OnErrorNotImplementedException.
        // in that case, need to using "Observer" instead of "Consumer"
        createObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        Timber.d("createObservable: accept");
                        Timber.d("list size: " + stringList.size());
                    }
                });

        // single is simple (not need handle onComplete)
        Single<List<String>> listSingle = Single.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return stringList;
            }
        });

        listSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.d("listSingle: onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<String> strings) {
                        Timber.d("listSingle: onNext");
                        Timber.d("list size: " + stringList.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("listSingle: onError");
                    }
                });


        // Subject is Observable and Observer
        PublishSubject<Integer> intSubject = PublishSubject.create();
        intSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer i) {
                Timber.d("intSubject: onNext");
                Timber.d("Integer : " + i);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

        for (int i=0 ; i<5 ; i++) {
            intSubject.onNext(i);
        }


        // map
        Single<String> stringSingle = Single.just(5).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer i) throws Exception {
                return String.valueOf(i);
            }
        });
        stringSingle.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(String s) {
                Timber.d("stringSingle: onNext");
                Timber.d("stringSingle : " + s);
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        // mix use
        // input a keyword to find a list
        // Note:
        // that subscribeOn has no practical effect on a PublishSubject because there is no
        // subscription side-effect happening in its subscribe() method.
        PublishSubject<String> mixUseSubject = PublishSubject.create();
        mixUseSubject
                .debounce(100, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(new Function<String, List<String>>() {
                    @Override
                    public List<String> apply(String s) throws Exception {
                        // using keyword to find data
                        Timber.d("mixUseSubject: Thread: " + Thread.currentThread().getName());
                        Timber.d("mixUseSubject: keyword: " + s);
                        return stringList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        // handle search result
                        Timber.d("mixUseSubject: Thread: " + Thread.currentThread().getName());
                        Timber.d("mixUseSubject: onNext");
                        Timber.d("mixUseSubject : " + strings.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        for (int i=0 ; i<3 ; i++) {
            mixUseSubject.onNext("abc" + i);
        }

        // flowable : 0..N flows, supporting Reactive-Streams and backpressure
        Flowable.just("Hello world").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
        Flowable.just("Hello world").subscribe(s -> System.out.println(s));
        Flowable.just("Hello world").subscribe(System.out::println);


        // flatMap
        Observable<String> flatMapObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("World!");
            }
        });

        flatMapObservable
                .flatMap(new Function<String, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(String s) throws Exception {
                        return Observable.just(s.length());
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) throws Exception {
                        Timber.d("flatMapObservable accept: " + i);
                    }
                });

        // concatMap
        flatMapObservable
                .concatMap(new Function<String, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(String s) throws Exception {
                        return Observable.just(s.length());
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) throws Exception {
                        Timber.d("concatMapObservable accept: " + i);
                    }
                });

        //
    }



}
