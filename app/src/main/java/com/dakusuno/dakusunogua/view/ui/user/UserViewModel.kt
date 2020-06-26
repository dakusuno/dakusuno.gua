package com.dakusuno.dakusunogua.view.ui.user

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import com.dakusuno.dakusunogua.base.LiveCoroutinesViewModel
import com.dakusuno.dakusunogua.model.User
import com.dakusuno.dakusunogua.repository.UserRepository
import timber.log.Timber

class UserViewModel constructor(private val userRepository:UserRepository):LiveCoroutinesViewModel(){
    val isLoading: ObservableBoolean = userRepository.isLoading
    var user:LiveData<User> = launchOnViewModelScope {
        Timber.d("ViewModel Scope")
        this.userRepository.fetchUser("dakusuno") {
            Log.d("result", it)
        }
    }
    fun getUser(username:String): LiveData<User> {
        Log.d("something","get user $username")
        return launchOnViewModelScope {
            Log.d("something","view model scope")
            this.userRepository.fetchUser(username) {
                Log.d("result", it)
            }
        }
    }

}