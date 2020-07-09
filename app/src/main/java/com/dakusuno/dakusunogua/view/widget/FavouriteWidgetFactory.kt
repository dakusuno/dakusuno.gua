package com.dakusuno.dakusunogua.view.widget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.model.User
import com.dakusuno.dakusunogua.persistence.UserDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class FavouriteWidgetFactory (val context: Context,val userDao: UserDao):RemoteViewsService.RemoteViewsFactory{

    private val listFavourite = mutableListOf<User>()

    override fun onCreate() {
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onDataSetChanged() {
       GlobalScope.launch {
           val user = userDao.getUserList()
           listFavourite.apply {
               clear()
               addAll(user)
               Timber.d(listFavourite.toString())
           }
       }
    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(context.packageName, R.layout.item_widget_favourite)
        val user = listFavourite.get(position)
        val extras = bundleOf(FavouriteWidget.EXTRA_ITEM to position)
        val fillIntent = Intent().putExtras(extras)
        Timber.d("halo getviewat")
        remoteViews.setTextViewText(R.id.widgetItemTaskNameLabel,user.login)
        remoteViews.setOnClickFillInIntent(R.id.widgetItemTaskNameLabel, fillIntent)
        return remoteViews
    }

    override fun getCount(): Int = listFavourite.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {
    }

}