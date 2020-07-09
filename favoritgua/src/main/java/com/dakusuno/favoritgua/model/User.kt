package com.dakusuno.favoritgua.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User (
                @PrimaryKey
                var login:String,
                var avatar_url:String,
                var name:String,
                var company:String,
                var location:String,
                var followers:Int,
                var following:Int,
                var public_repos:Int)
{
}