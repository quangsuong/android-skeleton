package vn.impl.projectbase.mainapp.ui.mainapp.fragments.homepage

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homePageModule = module {
    scope(named<HomePageFragment>()) {
        viewModel { HomePageViewModel() }
    }
}
