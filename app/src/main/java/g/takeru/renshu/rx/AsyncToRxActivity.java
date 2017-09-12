package g.takeru.renshu.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import g.takeru.renshu.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by takeru on 2016/12/7.
 *
 * This is a example for convert AsyncTask to RxJava
 */

public class AsyncToRxActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        byte[] data = new byte[10];
        savePhotoObservable2(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String uri) {
                        // after photo saved, do next step
                        Timber.d("uri: " + uri);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    // we do more io operation
    private String savePhoto(byte[] data){
        Timber.d("savePhoto");
        return "uri";
    }

    // one way: using create
    private Observable<String> savePhotoObservable(byte[] data){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(savePhoto(data));
                subscriber.onCompleted();
            }
        });
    }

    // another way: using defer
    private Observable<String> savePhotoObservable1(byte[] data){
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(savePhoto(data));
            }
        });
    }

    // using lambda and defer
    private Observable<String> savePhotoObservable2(byte[] data){
        return Observable.defer(() -> Observable.just(savePhoto(data)));
    }

}
