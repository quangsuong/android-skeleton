package vn.impl.projectbase.base.api

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import vn.impl.projectbase.base.databases.user.UserLocalDataSource
import vn.impl.projectbase.base.ultils.extentions.asListEntity
import vn.impl.projectbase.base.ultils.extentions.getDeviceId
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppInterceptor @Inject constructor(
    private val context: Context,
    private val moshi: Moshi,
    private val realmConfiguration: RealmConfiguration
) : Interceptor {

    /**
     * This class define header of api, please confirm with server to define header of api
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        val initRequest = chain.request()
        val deviceId = context.getDeviceId()

        /**
         * get access token and add into header request
         */
        var realm: Realm? = null
        try {
            realm = Realm.getInstance(realmConfiguration)
            val userDB = realm.where(UserLocalDataSource.TABLE_NAME)
                .findAll().toList()
            val users = userDB.asListEntity()
            if (users.isNotEmpty()) {
                Log.d("AppInterceptor", "${users[0].accessToken}")
            } else {
                Log.d("AppInterceptor", "user is empty")
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            realm?.close()
        }

        val request = initRequest.newBuilder()
            .addHeader("CLIENT_OS", "android")
            //.addHeader("APP_VERSION_NAME", BuildConfig.VERSION_NAME)
            //.addHeader("APP_VERSION_CODE", "${BuildConfig.VERSION_CODE}")
            //.addHeader("DEVICE_ID", deviceId)
            .build()
        return chain.proceed(request)
    }
}
