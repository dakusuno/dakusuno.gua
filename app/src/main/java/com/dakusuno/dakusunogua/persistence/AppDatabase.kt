package com.dakusuno.dakusunogua.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dakusuno.dakusunogua.model.User

@Database(entities = [User::class],version = 1,exportSchema = true)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
}