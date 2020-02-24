package vn.impl.projectbase.mainapp.application

import android.app.Application
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import vn.impl.projectbase.base.api.apiModule
import vn.impl.projectbase.base.databases.databaseModule
import vn.impl.projectbase.base.repository.repositoryModule
import vn.impl.projectbase.base.sharepreferences.sharedPreferencesModule
import vn.impl.projectbase.base.ultils.rx.reactivexModule
import vn.impl.projectbase.mainapp.injection.activityBindingModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            androidFileProperties() // need file assets/koin.properties to load
            modules(
                listOf(
                    apiModule,
                    databaseModule,
                    repositoryModule,
                    sharedPreferencesModule,
                    reactivexModule,
                    activityBindingModule
                )
            )
        }
    }
}
