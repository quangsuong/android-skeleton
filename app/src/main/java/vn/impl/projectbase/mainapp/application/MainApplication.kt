package vn.impl.projectbase.mainapp.application

import android.app.Activity
import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import vn.impl.projectbase.R
import vn.impl.projectbase.base.api.ApiModule
import javax.inject.Inject


class MainApplication : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        DaggerApplicationComponent.builder()
            .application(this)
            .apiModule(ApiModule(getString(R.string.base_url)))
            .build()
            .inject(this)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}
