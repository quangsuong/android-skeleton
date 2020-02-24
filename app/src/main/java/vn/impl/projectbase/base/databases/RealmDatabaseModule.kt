package vn.impl.projectbase.base.databases

import vn.impl.projectbase.base.databases.user.RealmUser
import io.realm.annotations.RealmModule

@RealmModule(classes = [RealmUser::class])
class RealmDatabaseModule
