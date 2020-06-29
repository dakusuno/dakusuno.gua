package com.dakusuno.dakusunogua.view.ui.user

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.dakusuno.dakusunogua.base.LiveCoroutinesViewModel
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.model.User
import com.dakusuno.dakusunogua.repository.FavouriteRepository
import com.dakusuno.dakusunogua.repository.UserRepository
import com.skydoves.whatif.whatIfNotNull
import timber.log.Timber

class UserViewModel constructor(private val userRepository:UserRepository,private val favouriteRepository: FavouriteRepository):LiveCoroutinesViewModel(){
    val userFetchingLiveData:MutableLiveData<String> = MutableLiveData()
    val isLoading: ObservableBoolean = userRepository.isLoading
    var isFavourite: ObservableBoolean = userRepository.isFavourite
    val user:LiveData<User>
    val follower:LiveData<List<Item>>
    val following:LiveData<List<Item>>
    init {
        Timber.d("init UserViewModel")
        user = userFetchingLiveData.switchMap {
            launchOnViewModelScope {
                this.userRepository.fetchUser(it){
                    Log.d("result", it)

                }
            }
        }
        follower = userFetchingLiveData.switchMap {
            launchOnViewModelScope {
                this.userRepository.fetchFollowers(it){
                    Timber.d(it)
                }
            }
        }
        following = userFetchingLiveData.switchMap {
            launchOnViewModelScope {
                this.userRepository.fetchFollowing(it){
                    Timber.d(it)
                }
            }
        }
        Timber.d(follower.value.toString())
    }

    fun getUser(username:String){
        userFetchingLiveData.value = username
    }
    fun favUser(){
        user.value.whatIfNotNull {
            favouriteRepository.setFavourite(it)
            isFavourite.set(true)
        }
    }
    fun notFavUser(){
        user.value.whatIfNotNull {
            favouriteRepository.deleteFavourite(it)
            isFavourite.set(false)
        }
    }
}