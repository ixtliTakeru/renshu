package g.takeru.renshu.rx;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Callable;

import g.takeru.renshu.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by takeru on 2016/12/7.
 *
 * This is a example for convert AsyncTask to RxJava
 *
 * ref: https://artemzin.com/blog/rxjava-defer-execution-of-function-via-fromcallable/
 */

public class AsyncToRxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        byte[] data = new byte[10];
        savePhotoObservable11(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.d("onSubscribe: " + d.isDisposed());
                    }

                    @Override
                    public void onNext(String uri) {
                        // after photo saved, do next step
                        Timber.d("onNext : uri: " + uri);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete:");
                    }
                });

    }

    // we do something io operation in background thread
    private String savePhoto(byte[] data){
        Timber.d("savePhoto");
        return "uri://xxx.xxx.x.xx.x.x.x";
    }

    // solution 1: using create (Rxjava2)
    private Observable<String> savePhotoObservable11(byte[] data){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(savePhoto(data));
//                emitter.onComplete();
//                emitter.isDisposed();
//                emitter.onError(new NoClassDefFoundError());
            }
        });
    }

    private Observable<String> savePhotoObservable12(byte[] data){
        return Observable.create(emitter -> emitter.onNext(savePhoto(data)));
    }

    // solution 2: using defer (Rxjava2)
    private Observable<String> savePhotoObservable2(byte[] data){
        return Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just(savePhoto(data));
            }
        });
    }

    // using lambda and defer (Rxjava2)
    private Observable<String> savePhotoObservable21(byte[] data){
        return Observable.defer(
                (Callable<ObservableSource<String>>) () -> Observable.just(savePhoto(data)));
    }

    // using lambda and defer
    private Observable<String> savePhotoObservable22(byte[] data){
        return Observable.defer(() -> Observable.just(savePhoto(data)));
    }

    // solution 3:
    // Note:
    // using "defer" still a lot of code plus that we have to handle the exception by ourselves.
    // instead of using "fromCallable" is more simple.
    private Observable<String> savePhotoObservable31(byte[] data){
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return savePhoto(data);
            }
        });
    }

    private Observable<String> savePhotoObservable32(byte[] data){
        return Observable.fromCallable(() -> savePhoto(data));
    }
}
