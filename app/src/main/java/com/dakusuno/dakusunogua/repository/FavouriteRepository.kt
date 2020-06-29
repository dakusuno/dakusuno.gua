package com.dakusuno.dakusunogua.repository

import androidx.databinding.ObservableBoolean
import com.dakusuno.dakusunogua.model.User
import com.dakusuno.dakusunogua.persistence.UserDao

class FavouriteRepository constructor(private val userDao: UserDao) : Repository{
    override var isLoading: ObservableBoolean = ObservableBoolean(false)
    override var isEmpty: ObservableBoolean = ObservableBoolean(false)

    fun setFavourite(user:User){
        userDao.insertUser(user)
    }
    fun deleteFavourite(user:User){
        userDao.deleteUser(user.login)
    }
}