package vn.impl.projectbase.base.api

import android.content.Context
import com.readystatesoftware.chuck.api.ChuckCollector
import com.readystatesoftware.chuck.api.ChuckInterceptor
import com.readystatesoftware.chuck.api.RetentionManager
import com.squareup.moshi.Moshi
import io.realm.RealmConfiguration
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import vn.impl.projectbase.BuildConfig
import vn.impl.projectbase.R
import vn.impl.projectbase.base.api.user.UserApi
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { provideHttpCache(androidContext()) }
    single { provideHttpLoggingInterceptor() }
    single { provideAppInterceptor(androidContext(), get(), get()) }
    single { provideMoshi() }
    single { provideOkHttpClient(androidContext(), get(), get(), get()) }
    single { provideRetrofit(androidContext().getString(R.string.base_url), get(), get()) }
    single { provideUserApi(get()) }
    //app interceptor
    single { AppInterceptor(androidContext(), get(), get()) }
}
const val CACHE_SIZE = 10L * 1024L * 1024L
const val TIME_OUT = 60L
const val CHUCK_MAX_CONTENT_LENGTH = 250000L

fun provideHttpCache(context: Context): Cache {
    return Cache(context.cacheDir, CACHE_SIZE)
}

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

fun provideAppInterceptor(
    context: Context,
    moshi: Moshi,
    realmConfiguration: RealmConfiguration
): Interceptor {
    return AppInterceptor(context, moshi, realmConfiguration)
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .build()
}

fun provideOkHttpClient(
    context: Context,
    cache: Cache,
    loggingInterceptor: HttpLoggingInterceptor,
    appInterceptor: AppInterceptor
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

fun provideRetrofit(baseUrl: String, moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun provideUserApi(retrofit: Retrofit): UserApi {
    return retrofit.create(UserApi::class.java)
}
