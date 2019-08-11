package vn.impl.projectbase.mainapp.application

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import vn.impl.projectbase.base.api.ApiModule
import vn.impl.projectbase.base.databases.DatabaseModule
import vn.impl.projectbase.base.repository.RepositoryModule
import vn.impl.projectbase.base.ui.AppViewModelModule
import vn.impl.projectbase.base.ultils.rx.ReactivexModule
import vn.impl.projectbase.mainapp.injection.ActivityBindingModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ApplicationModule::class,
        AppViewModelModule::class,
        ActivityBindingModule::class,
        ApiModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ReactivexModule::class]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder

        fun apiModule(apiModule: ApiModule): Builder

        fun build(): ApplicationComponent
    }
}
