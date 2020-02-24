package vn.impl.projectbase.base.sharepreferences

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single { provideAppConfigPreferences(androidContext()) }
}

fun provideAppConfigPreferences(context: Context): AppConfigPreferences {
    val sharedPreferences = context.getSharedPreferences(
        "impl.vn.timesheet.shared_preferences",
        Context.MODE_PRIVATE
    )
    return AppConfigPreferences(sharedPreferences)
}