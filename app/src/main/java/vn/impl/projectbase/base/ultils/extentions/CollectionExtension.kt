package vn.impl.projectbase.base.ultils.extentions;

import io.realm.RealmObject
import vn.impl.projectbase.base.databases.common.RealmObjectToEntity

fun <K, T : RealmObjectToEntity<K>> List<T>.asRealmObjectList(): List<RealmObject> {
    val newList = mutableListOf<RealmObject>()
    this.forEach { t: T -> newList.add(t.toRealmObject()) }
    return newList
}

fun <K, T : RealmObjectToEntity<K>> List<T>.asListEntity(): List<K> {
    val newList = mutableListOf<K>()
    this.forEach { realm: T -> newList.add(realm.toEntity()) }
    return newList
}


