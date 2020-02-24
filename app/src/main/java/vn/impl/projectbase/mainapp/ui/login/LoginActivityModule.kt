package vn.impl.projectbase.mainapp.ui.login

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginActivityModule = module {
    scope(named<LoginActivity>()) {
        viewModel { LoginViewModel(get()) }
    }
}
