package vn.impl.projectbase.base.repository.user

import android.util.Log
import vn.impl.projectbase.base.api.common.Result
import vn.impl.projectbase.base.api.user.request.PostRequest
import vn.impl.projectbase.base.api.user.response.GetResponse
import vn.impl.projectbase.base.api.user.response.PostResponse
import vn.impl.projectbase.base.entity.User
import io.reactivex.Observable

class UserRepository constructor(
     val userLocalDataSource: UserDataSource,
     val userRemoteDataSource: UserDataSource
) : UserDataSource {

    companion object {
        private val TAG = UserRepository::class.java.name
    }
    override fun getExample(id: Int): Observable<Result<GetResponse>> {
        Log.d(TAG, "getExample")
        return userRemoteDataSource.getExample(id)
    }

    override fun postExample(postRequest: PostRequest): Observable<Result<PostResponse>> {
        Log.d(TAG, "postExample")
        return userRemoteDataSource.postExample(postRequest)
    }

    override fun saveUser(user: User): Observable<Result<Boolean>> {
        Log.d(TAG, "saveUser")
        return userLocalDataSource.saveUser(user)
    }

    override fun getUser(): Observable<Result<User>> {
        Log.d(TAG, "getUser")
        return userLocalDataSource.getUser()
    }

    override fun deleteUser(): Observable<Result<Boolean>> {
        return userLocalDataSource.deleteUser()
    }

}
