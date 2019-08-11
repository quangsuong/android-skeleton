package vn.impl.projectbase.base.ultils.rx

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReactivexModule {

    @Singleton
    @Provides
    fun provideReactivexSchedulers(): ReactivexSchedulers {
        return AppReactivexSchedulers()
    }
}
