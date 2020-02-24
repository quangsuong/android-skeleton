package vn.impl.projectbase.mainapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import vn.impl.projectbase.base.api.common.ResultsObserver
import vn.impl.projectbase.base.api.user.response.GetResponse
import vn.impl.projectbase.base.entity.User
import vn.impl.projectbase.base.repository.user.UserRepository
import vn.impl.projectbase.base.ultils.extentions.plusAssign

class LoginViewModel constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    companion object {
        private val TAG = LoginViewModel::class.java.name
    }

    private val compositeDisposable = CompositeDisposable()

    private val testApi = MutableLiveData<GetResponse>()
    private val errorObserver = MutableLiveData<String>()
    private val isSavedUser = MutableLiveData<Boolean>()

    fun getTestApi(): LiveData<GetResponse> {
        return testApi
    }

    fun getErrorObserver(): LiveData<String> {
        return errorObserver
    }

    fun getIsSaveUser(): LiveData<Boolean> {
        return isSavedUser
    }

    fun checkGetApi(id: Int) {
        compositeDisposable += userRepository.getExample(id)
            .subscribeWith(GetApiObserver())
    }

    fun savedUser(user: User) {
        compositeDisposable += userRepository.saveUser(user).subscribeWith(SavedUserObserver())
    }

    private inner class GetApiObserver : ResultsObserver<GetResponse>() {
        override fun onSuccess(response: GetResponse) {
            Log.d(TAG, "GetApiObserver - onSuccess: $response")
            testApi.postValue(response)
            savedUser(User(0, response.accessToken))
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "GetApiObserver - onError: $e")
            errorObserver.postValue(e.message)
        }
    }

    private inner class SavedUserObserver : ResultsObserver<Boolean>() {
        override fun onSuccess(response: Boolean) {
            Log.d(TAG, "SavedUserObserver - onSuccess: $response")
            isSavedUser.postValue(true)
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "SavedUserObserver - onError: $e")
            errorObserver.postValue(e.message)
            isSavedUser.postValue(false)
        }
    }

}
