package nl.donyell.m2mobi

import io.reactivex.Single
import nl.donyell.m2mobi.response.GetPhotosResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {
    @GET("photos")
    fun getPhotos(): Single<List<GetPhotosResponse>>
}

object NetworkModule {

    private fun createInterceptorClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.PHOTOS_BASE_URL)
        .client(createInterceptorClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val service = retrofit.create(WebService::class.java)

}