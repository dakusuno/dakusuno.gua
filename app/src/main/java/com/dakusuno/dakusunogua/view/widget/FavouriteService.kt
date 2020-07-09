package com.dakusuno.dakusunogua.view.widget

import android.content.Intent
import android.widget.RemoteViewsService
import com.dakusuno.dakusunogua.persistence.UserDao
import org.koin.android.ext.android.get

class FavouriteService : RemoteViewsService(){
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        val userDao:UserDao = get()
        return FavouriteWidgetFactory(this.applicationContext,userDao)
    }
}