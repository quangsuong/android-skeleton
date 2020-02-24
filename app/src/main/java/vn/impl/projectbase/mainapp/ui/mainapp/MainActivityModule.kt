package vn.impl.projectbase.mainapp.ui.mainapp

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import vn.impl.projectbase.mainapp.ui.mainapp.fragments.homepage.homePageModule

val mainActivityModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get()) }

        //homepage fragment
        loadKoinModules(homePageModule)
    }
}
