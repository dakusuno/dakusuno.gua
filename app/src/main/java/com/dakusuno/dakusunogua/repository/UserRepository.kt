package com.dakusuno.dakusunogua.repository

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.dakusuno.dakusunogua.model.User
import com.dakusuno.dakusunogua.network.UserService
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class UserRepository constructor(private val userService: UserService):Repository{
    override var isLoading:ObservableBoolean = ObservableBoolean(false)
    init {
        Timber.d("Injection UserRepository")
    }
    suspend fun fetchUser(username: String,error: (String) -> Unit)= withContext(Dispatchers.IO){
        Timber.d("Fetch User")
        val liveData = MutableLiveData<User>()
        isLoading.set(true)
        userService.fetchUser(username).request {
            it.onSuccess {
                data.whatIfNotNull {
                    liveData.apply {
                        postValue(it)
                    }
                }
            }
                .onError {
                    error(message())
                }
                .onException {
                    error(message())
                }
            isLoading.set(false)
        }
        Timber.d(liveData.value.toString())
        liveData
    }
}