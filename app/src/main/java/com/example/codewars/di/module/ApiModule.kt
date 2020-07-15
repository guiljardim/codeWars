package com.example.codewars.di.module

import android.app.Application
import com.example.codewars.util.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        const val CACHE_HTTP = "http_cache"
        private const val CACHE_SIZE: Long = 50 * 1024 * 1024
        const val NAMED_INTERCEPTOR_CACHE_CONTROL = "NAMED_INTERCEPTOR_CACHE_CONTROL"
    }

    @Provides
    @Singleton
    fun provideCache(fileCache: File) = Cache(fileCache, CACHE_SIZE)

    @Provides
    @Singleton
    @Named(NAMED_INTERCEPTOR_CACHE_CONTROL)
    fun provideInterceptorCacheControl() = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val cacheControl = response.cacheControl

        return@Interceptor if (cacheControl.isPublic) response
        else response
            .newBuilder()
            .headers(response.headers)
            .build()
    }

    @Provides
    @Singleton
    fun provideFileCache(application: Application) = File(application.cacheDir, CACHE_HTTP)

    @Provides
    @Singleton
     fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        @Named(NAMED_INTERCEPTOR_CACHE_CONTROL) cacheControlInterceptor: Interceptor,
        cache: Cache): OkHttpClient =
            OkHttpClient
                .Builder()
                .addInterceptor(cacheControlInterceptor)
                .cache(cache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

}