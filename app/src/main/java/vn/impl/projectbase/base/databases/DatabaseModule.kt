package vn.impl.projectbase.base.databases

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import vn.impl.projectbase.base.ultils.extentions.getDeviceId
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        private val TAG = DatabaseModule::class.java.name

        private const val REALM_DATABASE_NAME = "impl.vn.timesheet"
        private const val REALM_DATABASE_VERSION = 1L
    }

    @Provides
    @Singleton
    fun provideRealmDatabaseConfig(
        context: Context,
        realmDatabaseModule: RealmDatabaseModule,
        realmDatabaseMigration: RealmDatabaseMigration
    ): RealmConfiguration {
        val deviceId = context.getDeviceId().toByteArray()
        val commonKey = "pom5ufap1as2zxce1093common_key-impl-vn-timesheet".toByteArray()
        val key = deviceId + commonKey
        return RealmConfiguration.Builder()
            .name(REALM_DATABASE_NAME)
            .schemaVersion(REALM_DATABASE_VERSION)
            .encryptionKey(key)
            .modules(realmDatabaseModule)
            .migration(realmDatabaseMigration)
            .build()
    }

    @Provides
    @Singleton
    fun provideRealmModule(): RealmDatabaseModule {
        return RealmDatabaseModule()
    }

    @Provides
    @Singleton
    fun provideRealmMigration(): RealmDatabaseMigration {
        return RealmDatabaseMigration()
    }

}
