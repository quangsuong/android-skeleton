package vn.impl.projectbase.base.ui

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * @see https://medium.com/@marco_cattaneo/android-viewmodel-and-factoryprovider-good-way-to-manage-it-with-dagger-2-d9e20a07084c
 * */

@Module
abstract class AppViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
