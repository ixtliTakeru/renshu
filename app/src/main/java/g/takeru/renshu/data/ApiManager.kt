package g.takeru.renshu.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by takeru on 2018/3/26.
 */

class ApiManager {

    private var baseUrl = "https://en.wikipedia.org/w/"
    var api: Api = initRetrofit()

    private object Holder {
        val INSTANCE = ApiManager()
    }

    companion object {
        val instance: ApiManager by lazy { Holder.INSTANCE }
    }

    private fun initRetrofit(): Api {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)
        builder.interceptors().add(interceptor)

        var retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()
        return retrofit.create(Api::class.java)
    }
}
