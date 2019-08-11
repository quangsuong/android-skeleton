package vn.impl.projectbase.mainapp.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vn.impl.projectbase.base.injection.ActivityScope
import vn.impl.projectbase.mainapp.ui.login.LoginActivity
import vn.impl.projectbase.mainapp.ui.login.LoginActivityModule
import vn.impl.projectbase.mainapp.ui.mainapp.MainActivity
import vn.impl.projectbase.mainapp.ui.mainapp.MainActivityModule
import vn.impl.projectbase.mainapp.ui.splash.SplashActivity

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun getSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun getMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun getLoginActivity(): LoginActivity

}
