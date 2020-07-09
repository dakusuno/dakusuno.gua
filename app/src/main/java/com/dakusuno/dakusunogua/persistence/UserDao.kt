package com.dakusuno.dakusunogua.persistence

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dakusuno.dakusunogua.model.User

@Dao
interface UserDao{
    @Query("SELECT * FROM User Where login = :login")
    fun getUser(login:String): User

    @Query("SELECT * FROM User")
    fun getUserList(): List<User>

    @Query("SELECT * FROM User")
    fun selectAll(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:User)

    @Query("DELETE FROM User WHERE login = :login")
    fun deleteUser(login:String)
}