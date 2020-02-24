package vn.impl.projectbase.base.databases.common

import io.realm.RealmObject

interface RealmObjectToEntity<T> {
    fun toEntity(): T
    fun toRealmObject(): RealmObject
}
