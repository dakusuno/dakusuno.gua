package com.dakusuno.dakusunogua.persistence

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(user:User)

    @Query("DELETE FROM User WHERE login = :login")
    fun deleteNote(login:String)
}