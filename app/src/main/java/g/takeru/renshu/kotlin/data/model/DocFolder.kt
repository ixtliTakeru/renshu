package g.takeru.renshu.kotlin.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DocFolder (var version: String,
                      var data: CountryData) : Parcelable

/**
 * Kotlin var does not save generic type (deserialized to LinkedTreeMap instead)
 * ref: https://github.com/google/gson/issues/1101
 *
 * if you not add "@JvmSuppressWildcards", you will got the
 * "java.lang.ClassCastException: com.google.gson.internal.LinkedTreeMap cannot be cast to ...."
 *
 * The problem is that you're using Kotlin and Kotlin will by default translate a Set<X> into a
 * Set<? extends X>
 * ref: http://kotlinlang.org/docs/reference/java-to-kotlin-interop.html#variant-generics
 * ref: https://github.com/google/dagger/issues/668#issuecomment-289713497
 */
@Parcelize
data class CountryData(var country: Map<String, @JvmSuppressWildcards List<CityData>>,
                   var non_country: Map<String, @JvmSuppressWildcards List<CityData>>) : Parcelable

@Parcelize
data class CityData(val key: String,
                    val label: String,
                    val category_list: List<CategoryData>) : Parcelable

@Parcelize
data class CategoryData(val key: String,
                        val label: String,
                        val list: List<Detail>) : Parcelable

/**
 * if your parameter is nullable, please add null safety check "?"
 * otherwise you will get exception " ... Parameter specified as non-null is null:
 * method kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull, parameter type ..."
 */
@Parcelize
data class Detail(val key: String,
                  val label: String,
                  val set: String,
                  val type: String? = "") : Parcelable

