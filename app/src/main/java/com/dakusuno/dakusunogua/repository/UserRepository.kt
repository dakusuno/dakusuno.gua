package com.dakusuno.dakusunogua.repository

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.model.User
import com.dakusuno.dakusunogua.network.ItemService
import com.dakusuno.dakusunogua.network.UserService
import com.dakusuno.dakusunogua.persistence.UserDao
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class UserRepository constructor(private val userService: UserService,private val itemService: ItemService,private val userDao: UserDao):Repository{
    override var isLoading:ObservableBoolean = ObservableBoolean(false)
    override var isEmpty:ObservableBoolean = ObservableBoolean(false)
    var isFavourite:ObservableBoolean = ObservableBoolean(false)

    init {
        Timber.d("Injection UserRepository")
    }
    suspend fun fetchUser(username: String,error: (String) -> Unit)= withContext(Dispatchers.IO){
        Timber.d("Fetch User")
        val liveData = MutableLiveData<User>()
        val user = userDao.getUser(username)
        isLoading.set(true)
        userService.fetchUser(username).request {
            it.onSuccess {
                data.whatIfNotNull {
                    if(it.company.isNullOrBlank()){
                        it.company = "-"
                    }
                    if(it.location.isNullOrBlank()){
                        it.location = "-"
                    }
                    if(it.name.isNullOrBlank()){
                        it.name = "Anonymous"
                    }
                    when(user){
                        null -> isFavourite.set(false)
                        else -> isFavourite.set(true)
                    }
                    liveData.postValue(it)
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
    suspend fun fetchFollowers(username: String,error: (String) -> Unit)= withContext(Dispatchers.IO){
        val liveData = MutableLiveData<List<Item>>()
        itemService.fetchFollowers(username).request {
            it.onSuccess {
                data.whatIfNotNull {
                    liveData.postValue(it)
                    Timber.d(it.toString())
                }
            }
                .onError { error(message()) }
                .onException { error(message()) }
        }
        liveData
    }
    suspend fun fetchFollowing(username:String,error: (String) -> Unit) = withContext(Dispatchers.IO){
        val liveData = MutableLiveData<List<Item>>()
        itemService.fetchFollowing(username).request {
            it.onSuccess {
                data.whatIfNotNull {
                    liveData.postValue(it)
                }
            }
                .onError { error(message())  }
                .onException { error(message()) }
        }
        liveData
    }

}