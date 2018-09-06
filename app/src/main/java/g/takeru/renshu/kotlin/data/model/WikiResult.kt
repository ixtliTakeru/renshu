package g.takeru.renshu.kotlin.data.model

class WikiResult(val query: Query) {
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}