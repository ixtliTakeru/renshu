package g.takeru.renshu.data

import g.takeru.renshu.data.model.WikiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by takeru on 2018/3/26.
 */

interface Api {

    @GET("api.php")
    fun getDataFromWiki(@Query("action") action: String,
                        @Query("format") format: String,
                        @Query("list") list: String,
                        @Query("srsearch") srsearch: String)
            : Observable<Response<WikiResult>>
}
