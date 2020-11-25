package vn.impl.projectbase.mainapp.application

import android.annotation.SuppressLint
import android.content.Context
import dagger.Module
import dagger.Provides
import vn.impl.projectbase.base.sharepreferences.AppConfigPreferences
import javax.inject.Singleton

@Module
class ApplicationModule {

    companion object {
        private val TAG = ApplicationModule::class.java.name
    }

    @Singleton
    @Provides
    fun provideMainApplication(app: MainApplication): Context {
        return app
    }

    @SuppressLint("HardwareIds")
    @Singleton
    @Provides
    fun provideAppConfigPreference(context: Context): AppConfigPreferences {
        val sharedPreferences = context.getSharedPreferences(
            "impl.vn.timesheet.shared_preferences",
            Context.MODE_PRIVATE
        )
        return AppConfigPreferences(sharedPreferences)
    }

}
