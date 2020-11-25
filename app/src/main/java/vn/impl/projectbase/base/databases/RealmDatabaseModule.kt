package vn.impl.projectbase.base.databases

import io.realm.annotations.RealmModule
import vn.impl.projectbase.base.databases.user.RealmUser

@RealmModule(classes = [RealmUser::class])
class RealmDatabaseModule
