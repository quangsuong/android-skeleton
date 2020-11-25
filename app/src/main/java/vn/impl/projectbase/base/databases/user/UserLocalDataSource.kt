package vn.impl.projectbase.base.databases.user

import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmConfiguration
import vn.impl.projectbase.base.api.common.Result
import vn.impl.projectbase.base.api.user.request.PostRequest
import vn.impl.projectbase.base.api.user.response.GetResponse
import vn.impl.projectbase.base.api.user.response.PostResponse
import vn.impl.projectbase.base.databases.BaseDatabase
import vn.impl.projectbase.base.entity.User
import vn.impl.projectbase.base.repository.user.UserDataSource
import vn.impl.projectbase.base.ultils.extentions.asListEntity
import vn.impl.projectbase.base.ultils.rx.ReactivexSchedulers
import javax.inject.Inject

/**
 * @see https://academy.realm.io/posts/creating-a-reactive-data-layer-with-realm-and-rxjava2/
 * */
class UserLocalDataSource @Inject constructor(
    private val realmConfiguration: RealmConfiguration,
    private val rxSchedulers: ReactivexSchedulers
) : UserDataSource, BaseDatabase {

    companion object {
        private val TAG = UserLocalDataSource::class.java.name

        val TABLE_NAME = RealmUser::class.java
    }

    override fun getExample(id: Int): Observable<Result<GetResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postExample(postRequest: PostRequest): Observable<Result<PostResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUser(user: User): Observable<Result<Boolean>> {
        val realmObservable = Observable.create<Result<Boolean>> { emitter ->
            var realm: Realm? = null
            try {
                realm = Realm.getInstance(realmConfiguration)
                realm?.use { realm: Realm? ->
                    realm?.executeTransaction { realm: Realm ->
                        val userRealm = user.toRealmObject()
                        realm.insert(userRealm)
                        emitter.onNext(Result(true, null))
                        emitter.onComplete()
                    }
                }
            } catch (throwable: Throwable) {
                emitter.onError(throwable)
            } finally {
                realm?.close()
            }
        }
        return realmObservable.subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

    override fun getUser(): Observable<Result<User>> {
        val realmObservable = Observable.create<Result<User>> { emitter ->
            var realm: Realm? = null
            try {
                realm = Realm.getInstance(realmConfiguration)
                val userDB = realm.where(TABLE_NAME)
                    .findAll().toList()
                val users = userDB.asListEntity()
                if (users.isNotEmpty()) {
                    emitter.onNext(Result(users[0], null))
                    emitter.onComplete()
                } else {
                    emitter.onError(Throwable("can not get user data"))
                }
            } catch (throwable: Throwable) {
                emitter.onError(throwable)
            } finally {
                realm?.close()
            }
        }
        return realmObservable
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

    override fun deleteUser(): Observable<Result<Boolean>> {
        val realmObservable = Observable.create<Result<Boolean>> { emitter ->
            var realm: Realm? = null
            try {
                realm = Realm.getInstance(realmConfiguration)
                val userDB = realm.where(TABLE_NAME).findAll()
                realm?.beginTransaction()
                val isDeleted = userDB.deleteAllFromRealm()
                realm?.commitTransaction()
                emitter.onNext(Result(isDeleted, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                emitter.onError(throwable)
            } finally {
                realm?.close()
            }
        }
        return realmObservable
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

}
