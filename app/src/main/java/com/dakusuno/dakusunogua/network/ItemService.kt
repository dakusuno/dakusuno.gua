package com.dakusuno.dakusunogua.network

import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.model.ItemList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemService {
    @GET("search/users")
    fun fetchSearchItemList(@Query("q") username:String):Call<ItemList>

    @GET("users/{username}/followers")
    fun fetchFollowers(@Path("username") username:String):Call<List<Item>>

    @GET("users/{username}/following")
    fun fetchFollowing(@Path("username") username:String):Call<List<Item>>
}