package vn.impl.projectbase.base.ultils.rx

import org.koin.dsl.bind
import org.koin.dsl.module


val reactivexModule = module {
    single { AppReactivexSchedulers() } bind ReactivexSchedulers::class
}
