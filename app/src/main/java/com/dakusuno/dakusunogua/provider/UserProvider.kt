package com.dakusuno.dakusunogua.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dakusuno.dakusunogua.persistence.UserDao
import org.koin.android.ext.android.get

class UserProvider : ContentProvider(){

    val AUTHORITY:String = "com.dakusuno.dakusunogua.provider"


    /** The match code for some items in the Menu table.  */
    private val CODE_MENU_DIR = 1

    /** The match code for an item in the Menu table.  */
    private val CODE_MENU_ITEM = 2

    private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

    init {
        MATCHER.addURI(AUTHORITY,"/user",CODE_MENU_DIR)
        MATCHER.addURI(AUTHORITY,"/user"+"/*",CODE_MENU_ITEM)
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw IllegalArgumentException("Unknown URI: " + uri)
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val userDao : UserDao = get()
        val cursor = when(MATCHER.match(uri)){
            CODE_MENU_DIR -> userDao.selectAll()
            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }
        cursor.setNotificationUri(context?.contentResolver,uri)
        return cursor
    }

    override fun onCreate() = true
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw IllegalArgumentException("Unknown URI: " + uri)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw IllegalArgumentException("Unknown URI: " + uri)
    }

    override fun getType(uri: Uri): String? {
        throw IllegalArgumentException("Unknown URI: " + uri)
    }

}