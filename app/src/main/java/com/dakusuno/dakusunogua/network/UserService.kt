package com.dakusuno.dakusunogua.network

import com.dakusuno.dakusunogua.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService{
    @GET("users/{username}")
    fun fetchUser(@Path("username") username:String):Call<User>

}