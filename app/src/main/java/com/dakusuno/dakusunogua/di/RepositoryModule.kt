package com.dakusuno.dakusunogua.di

import com.dakusuno.dakusunogua.repository.FavouriteRepository
import com.dakusuno.dakusunogua.repository.ItemRepository
import com.dakusuno.dakusunogua.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        ItemRepository(get())
    }
    single {
        UserRepository(get(),get(),get())
    }
    single {
        FavouriteRepository(get())
    }
}