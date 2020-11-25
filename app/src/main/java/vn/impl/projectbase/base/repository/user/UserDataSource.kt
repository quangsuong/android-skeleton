package vn.impl.projectbase.base.repository.user

import io.reactivex.Observable
import vn.impl.projectbase.base.api.common.Result
import vn.impl.projectbase.base.api.user.request.PostRequest
import vn.impl.projectbase.base.api.user.response.GetResponse
import vn.impl.projectbase.base.api.user.response.PostResponse
import vn.impl.projectbase.base.entity.User

interface UserDataSource {

    fun getExample(id: Int): Observable<Result<GetResponse>>

    fun postExample(postRequest: PostRequest): Observable<Result<PostResponse>>

    fun saveUser(user: User): Observable<Result<Boolean>>

    fun getUser(): Observable<Result<User>>

    fun deleteUser(): Observable<Result<Boolean>>

}
