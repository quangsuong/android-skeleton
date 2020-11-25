package vn.impl.projectbase.base.api.user

import io.reactivex.Observable
import vn.impl.projectbase.base.api.common.Result
import vn.impl.projectbase.base.api.common.retrofitResponseToResult
import vn.impl.projectbase.base.api.user.request.PostRequest
import vn.impl.projectbase.base.api.user.response.GetResponse
import vn.impl.projectbase.base.api.user.response.PostResponse
import vn.impl.projectbase.base.entity.User
import vn.impl.projectbase.base.repository.user.UserDataSource
import vn.impl.projectbase.base.ultils.rx.ReactivexSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(

    private val userApi: UserApi,
    private val rxScheduler: ReactivexSchedulers

) : UserDataSource {

    override fun getExample(id: Int): Observable<Result<GetResponse>> {
        return userApi.getExample(id)
            .retrofitResponseToResult()
            .subscribeOn(rxScheduler.io())
            .observeOn(rxScheduler.androidMainThread())
    }

    override fun postExample(postRequest: PostRequest): Observable<Result<PostResponse>> {
        return userApi.postExample(postRequest)
            .retrofitResponseToResult()
            .subscribeOn(rxScheduler.io())
            .observeOn(rxScheduler.androidMainThread())
    }

    override fun saveUser(user: User): Observable<Result<Boolean>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(): Observable<Result<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUser(): Observable<Result<Boolean>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
