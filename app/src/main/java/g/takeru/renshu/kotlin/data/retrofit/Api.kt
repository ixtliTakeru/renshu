package g.takeru.renshu.kotlin.data.retrofit

import g.takeru.renshu.kotlin.data.model.WikiResult
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by takeru on 2018/3/26.
 */

interface Api {

    @GET("/w/api.php")
    fun getDataFromWiki(@Query("action") action: String,
                        @Query("format") format: String,
                        @Query("list") list: String,
                        @Query("srsearch") srsearch: String)
            : Observable<Response<WikiResult>>
}
