package vn.impl.projectbase.mainapp.ui.mainapp

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import vn.impl.projectbase.base.injection.ActivityScope
import vn.impl.projectbase.base.injection.FragmentScope
import vn.impl.projectbase.base.injection.ViewModelKey
import vn.impl.projectbase.mainapp.ui.mainapp.fragments.homepage.HomePageFragment
import vn.impl.projectbase.mainapp.ui.mainapp.fragments.homepage.HomePageModule

@Module
abstract class MainActivityModule {

    /**
     * @see https://medium.com/@marco_cattaneo/android-viewmodel-and-factoryprovider-good-way-to-manage-it-with-dagger-2-d9e20a07084c
     * */
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @ActivityScope
    abstract fun appCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomePageModule::class])
    abstract fun providerPageHomeFragment(): HomePageFragment
}
