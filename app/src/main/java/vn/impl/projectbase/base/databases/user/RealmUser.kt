package vn.impl.projectbase.base.databases.user

import vn.impl.projectbase.base.databases.common.RealmObjectToEntity
import vn.impl.projectbase.base.entity.User
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmUser(

    @PrimaryKey
    open var id: Long = -1,

    open var accessToken: String? = null

) : RealmObject(), RealmObjectToEntity<User> {

    constructor(entity: User) : this(
        id = entity.id ?: -1,
        accessToken = entity.accessToken
    )

    val entity: User
        get() = User(
            id = id,
            accessToken = accessToken
        )

    override fun toEntity(): User {
        return entity
    }

    override fun toRealmObject(): RealmObject {
        return this
    }
}



