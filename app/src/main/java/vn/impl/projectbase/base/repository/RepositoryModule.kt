package vn.impl.projectbase.base.repository

import org.koin.core.context.loadKoinModules
import org.koin.dsl.bind
import org.koin.dsl.module
import vn.impl.projectbase.base.api.user.UserRemoteDataSource
import vn.impl.projectbase.base.databases.user.UserLocalDataSource
import vn.impl.projectbase.base.repository.user.UserDataSource
import vn.impl.projectbase.base.repository.user.UserRepository
import vn.impl.projectbase.base.repository.user.userRepositoryModule

val repositoryModule = module {
    //user_repository module
    loadKoinModules(userRepositoryModule)
    single {
        UserRepository(
            getKoin().bind<UserDataSource, UserLocalDataSource>(),
            getKoin().bind<UserDataSource, UserRemoteDataSource>()
        )
    } bind UserDataSource::class
}
