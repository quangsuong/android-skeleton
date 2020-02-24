package vn.impl.projectbase.base.repository.user

import org.koin.dsl.bind
import org.koin.dsl.module
import vn.impl.projectbase.base.api.user.UserRemoteDataSource
import vn.impl.projectbase.base.databases.user.UserLocalDataSource

val userRepositoryModule = module {
    single { UserRemoteDataSource(get(), get()) } bind UserDataSource::class
    single { UserLocalDataSource(get(), get()) } bind UserDataSource::class
}