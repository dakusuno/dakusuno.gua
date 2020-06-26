package com.dakusuno.dakusunogua.di

import com.dakusuno.dakusunogua.network.ItemService
import com.dakusuno.dakusunogua.network.RequestInterceptor
import com.dakusuno.dakusunogua.network.UserService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(ItemService::class.java) }
    single { get<Retrofit>().create(UserService::class.java) }
}