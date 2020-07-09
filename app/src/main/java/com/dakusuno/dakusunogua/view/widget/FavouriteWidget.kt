package com.dakusuno.dakusunogua.view.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.dakusuno.dakusunogua.R

class FavouriteWidget : AppWidgetProvider(){
    companion object{
        const val EXTRA_ITEM = "com.dakusuno.dakusunogua.EXTRA_ITEM"
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager?, appWidgetId : Int){
            val intent  = Intent(context, FavouriteService::class.java).apply {
                    putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId)
                data = toUri(Intent.URI_INTENT_SCHEME).toUri()
            }
            val views = RemoteViews(context.packageName, R.layout.widget_favourite).apply {
                setRemoteAdapter(R.id.widgetListView,intent)
                setEmptyView(R.id.widgetListView,R.id.empty_view)
            }
            appWidgetManager?.updateAppWidget(appWidgetId,views)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray
    ) {
        for(i in appWidgetIds){
            updateAppWidget(context,appWidgetManager,i)
        }
    }
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if(intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE){
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val widget = ComponentName(context, FavouriteWidget::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(widget)
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widgetListView)
        }
    }
}