package com.dakusuno.dakusunogua.model

data class User(var login:String,var avatar_url:String,var name:String="Anonymous", var company:String="-",var location:String = "-",var followers:Int,var following:Int,var public_repos:Int)