package vn.impl.projectbase.base.repository

import dagger.Module
import vn.impl.projectbase.base.repository.user.UserRepositoryModule

@Module(includes = [UserRepositoryModule::class])
abstract class RepositoryModule {
}
