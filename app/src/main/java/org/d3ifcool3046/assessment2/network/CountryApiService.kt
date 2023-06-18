package org.d3ifcool3046.assessment2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3ifcool3046.assessment2.model.Country
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "muhammadazra4503/country_flags/master/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface CountryApiService {
    @GET("static-api.json")
    suspend fun getCountry(): List<Country>
}
object CountryApi {
    val service: CountryApiService by lazy {
        retrofit.create(CountryApiService::class.java)
    }
    fun getCountryUrl(image: String): String {
        return "$BASE_URL$image.png"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }
