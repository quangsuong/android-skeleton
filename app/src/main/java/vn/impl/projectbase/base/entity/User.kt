package vn.impl.projectbase.base.entity

import com.squareup.moshi.Json
import io.realm.RealmObject
import vn.impl.projectbase.base.databases.common.RealmObjectToEntity
import vn.impl.projectbase.base.databases.user.RealmUser

open class User(

    @field:Json(name = "id")
    val id: Long?,
    @field:Json(name = "access_token")
    val accessToken: String?

) : RealmObjectToEntity<User> {
    override fun toEntity(): User {
        return this
    }

    override fun toRealmObject(): RealmObject {
        return RealmUser(this)
    }

}

