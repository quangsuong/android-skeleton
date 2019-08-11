package vn.impl.projectbase.base.api

import android.content.Context
import com.readystatesoftware.chuck.api.ChuckCollector
import com.readystatesoftware.chuck.api.ChuckInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.readystatesoftware.chuck.api.RetentionManager
import vn.impl.projectbase.base.api.user.UserApi
import vn.impl.projectbase.base.injection.BaseApi
import io.realm.RealmConfiguration
import vn.impl.projectbase.BuildConfig


@Module
class ApiModule(private val baseUrl: String) {

    companion object {
        private val TAG = ApiModule::class.java.name

        private const val CACHE_SIZE = 10L * 1024L * 1024L
        private const val TIME_OUT = 60L

        private const val CHUCK_MAX_CONTENT_LENGTH=250000L
    }

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): Cache {
        val cache = Cache(context.cacheDir, CACHE_SIZE)
        return cache
    }

    @Provides
    @Singleton
    @BaseApi
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return loggingInterceptor
    }

    @Provides
    @Singleton
    @BaseApi
    fun provideAppInterceptor(
        context: Context,
        moshi: Moshi,
        realmConfiguration: RealmConfiguration
    ): Interceptor {
        return AppInterceptor(context, moshi, realmConfiguration)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @Singleton
    @BaseApi
    fun provideOkHttpClient(context: Context,
                            cache: Cache,
                            @BaseApi loggingInterceptor: HttpLoggingInterceptor,
                            @BaseApi appInterceptor: Interceptor
    ): OkHttpClient {

        // Collector
        val collector = ChuckCollector(context)
            .showNotification(true)
            .retentionManager(RetentionManager(context, ChuckCollector.Period.FOREVER))

        // Interceptor
        val chuckerInterceptor = ChuckInterceptor(context, collector)
            .maxContentLength(CHUCK_MAX_CONTENT_LENGTH)

        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(appInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @BaseApi
    fun provideRetrofit(moshi: Moshi, @BaseApi okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginApi(@BaseApi retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}
