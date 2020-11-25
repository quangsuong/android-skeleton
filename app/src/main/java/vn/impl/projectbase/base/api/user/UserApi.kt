package vn.impl.projectbase.base.api.user

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import vn.impl.projectbase.base.api.user.request.PostRequest
import vn.impl.projectbase.base.api.user.response.GetResponse
import vn.impl.projectbase.base.api.user.response.PostResponse

interface UserApi {

    @GET("get/example")
    fun getExample(@Query("id") id: Int): Observable<GetResponse>

    @POST("post/example")
    fun postExample(@Body body: PostRequest): Observable<PostResponse>

}
