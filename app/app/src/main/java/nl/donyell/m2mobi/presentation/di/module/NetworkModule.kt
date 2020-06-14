package nl.donyell.m2mobi.presentation.di.module

import dagger.Module
import dagger.Provides
import nl.donyell.m2mobi.BuildConfig
import nl.donyell.m2mobi.data.repository.cloud.JsonPlaceholderApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideJsonPlaceholderApi(okHttpClient: OkHttpClient): JsonPlaceholderApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.PHOTOS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(JsonPlaceholderApi::class.java)
    }

}