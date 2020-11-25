package vn.impl.projectbase.base.databases

import io.realm.DynamicRealm
import io.realm.RealmMigration

class RealmDatabaseMigration : RealmMigration {

    override fun equals(other: Any?): Boolean {
        return other is RealmDatabaseMigration
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

    }
}
