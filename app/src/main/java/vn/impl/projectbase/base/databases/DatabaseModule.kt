package vn.impl.projectbase.base.databases

import android.content.Context
import vn.impl.projectbase.base.ultils.extentions.getDeviceId
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { provideRealmDatabaseConfig(androidContext(), get(), get()) }
    single { provideRealmModule() }
    single { provideRealmMigration() }
}

private const val REALM_DATABASE_NAME = "impl.vn.timesheet"
private const val REALM_DATABASE_VERSION = 1L

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

fun provideRealmModule(): RealmDatabaseModule {
    return RealmDatabaseModule()
}

fun provideRealmMigration(): RealmDatabaseMigration {
    return RealmDatabaseMigration()
}
