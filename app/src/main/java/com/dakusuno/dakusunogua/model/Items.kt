package com.dakusuno.dakusunogua.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(var login:String,var avatar_url:String):Parcelable

data class ItemList(var items:List<Item>)