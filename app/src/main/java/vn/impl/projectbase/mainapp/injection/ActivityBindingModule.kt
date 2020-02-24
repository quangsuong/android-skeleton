package vn.impl.projectbase.mainapp.injection

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import vn.impl.projectbase.mainapp.ui.login.loginActivityModule
import vn.impl.projectbase.mainapp.ui.mainapp.mainActivityModule

val activityBindingModule = module {
    //main activity
    loadKoinModules(mainActivityModule)

    //login activity
    loadKoinModules(loginActivityModule)
}
