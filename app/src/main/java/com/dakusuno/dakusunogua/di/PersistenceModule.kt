package com.dakusuno.dakusunogua.di

import androidx.room.Room
import com.dakusuno.dakusunogua.persistence.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {
    single {
        Room.databaseBuilder(androidApplication(),AppDatabase::class.java,"Dakusunonote.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<AppDatabase>().userDao()
    }
}