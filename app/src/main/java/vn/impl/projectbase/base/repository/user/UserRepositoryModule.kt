package vn.impl.projectbase.base.repository.user

import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import vn.impl.projectbase.base.api.user.UserApi
import vn.impl.projectbase.base.api.user.UserRemoteDataSource
import vn.impl.projectbase.base.databases.user.UserLocalDataSource
import vn.impl.projectbase.base.injection.SourceLocal
import vn.impl.projectbase.base.injection.SourceRemote
import vn.impl.projectbase.base.ultils.rx.ReactivexSchedulers
import javax.inject.Singleton

@Module
class UserRepositoryModule {

    @Provides
    @Singleton
    @SourceRemote
    fun provideUserRemoteDataSource(
        userApi: UserApi,
        rxSchedulers: ReactivexSchedulers
    ): UserDataSource {
        return UserRemoteDataSource(userApi, rxSchedulers)
    }

    @Provides
    @Singleton
    @SourceLocal
    fun provideUserLocalDataSource(
        realmConfiguration: RealmConfiguration,
        rxSchedulers: ReactivexSchedulers
    ): UserDataSource {
        return UserLocalDataSource(realmConfiguration, rxSchedulers)
    }
}
