package g.takeru.renshu.kotlin.data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by takeru on 2018/3/26.
 */

class ApiManager {

    /**
     * note:
     * When you occur "IllegalArgumentException("baseUrl must end in /: ")",
     * please check baseUrl again.
     * ref: https://www.jianshu.com/p/d6b8b6bc6209
     */

    private var baseUrl = "https://te.wikipedia.org"
    var api: Api = initRetrofit()
    lateinit var hostSelectionInterceptor: HostSelectionInterceptor

    private object Holder {
        val INSTANCE = ApiManager()
    }

    companion object {
        val instance: ApiManager by lazy { Holder.INSTANCE }
    }

    private fun initRetrofit(): Api {
        // log interceptor
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // dynamic hostname interceptor
        hostSelectionInterceptor = HostSelectionInterceptor()

        // okHttp interceptor for add header
        val headerInterceptor = Interceptor { chain ->
            var request = chain.request()
            var requestBuilder = request.newBuilder().method(request.method(), request.body())
//            requestBuilder.addHeader("deviceId", "xxxxx")
            return@Interceptor chain.proceed(requestBuilder.build())
        }

        val builder = OkHttpClient.Builder()
        builder.connectTimeout(1, TimeUnit.MINUTES)
        builder.readTimeout(1, TimeUnit.MINUTES)
        builder.interceptors().add(logInterceptor)
        builder.interceptors().add(hostSelectionInterceptor)
//        builder.interceptors().add(headerInterceptor)

        var retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(Api::class.java)
    }
}

class HostSelectionInterceptor : Interceptor {
    @Volatile
    private var host: String? = null

    fun setHost(host: String) {
        this.host = host
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        val host = this.host
        if (host != null) {
            val newUrl = request.url().newBuilder()
                    .host(host)
                    .build()
            request = request.newBuilder()
                    .url(newUrl)
                    .build()
        }
        return chain.proceed(request)
    }
}

