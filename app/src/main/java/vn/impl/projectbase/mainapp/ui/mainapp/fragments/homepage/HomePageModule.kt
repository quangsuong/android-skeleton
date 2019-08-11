package vn.impl.projectbase.mainapp.ui.mainapp.fragments.homepage

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import vn.impl.projectbase.base.injection.ViewModelKey

@Module
abstract class HomePageModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel::class)
    abstract fun bindPageHomeViewModel(homePageViewModel: HomePageViewModel): ViewModel

}
