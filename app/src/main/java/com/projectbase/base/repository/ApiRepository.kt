package com.projectbase.base.repository

import android.content.Context
import com.projectbase.base.api.AppApi
import com.projectbase.base.api.model.GetResponse
import com.projectbase.base.api.model.PostRequest
import com.projectbase.base.api.model.PostResponse
import com.projectbase.base.datahandling.Result
import com.projectbase.base.datahandling.retrofitResponseToResult
import com.projectbase.base.ultils.rx.AppReactivexSchedulers
import io.reactivex.Observable

class ApiRepository(
    private val context: Context,
    private val appApi: AppApi,
    private val rxSchedulers: AppReactivexSchedulers
) {
    fun postExample(body: PostRequest): Observable<Result<PostResponse>> {
        return appApi.postExample(body)
            .retrofitResponseToResult()
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

    fun getExample(id: Int): Observable<Result<GetResponse>> {
        return appApi.getExample(id)
            .retrofitResponseToResult()
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }
}
