package com.dakusuno.favoritgua

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dakusuno.favoritgua.model.User
import com.skydoves.whatif.whatIfNotNull
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var listuser = arrayListOf<User>()
    companion object {
        val CONTENT_URI: Uri = Uri.Builder().scheme("content")
            .authority("com.dakusuno.dakusunogua.provider")
            .appendPath("user")
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val cursor = contentResolver?.query(CONTENT_URI,null,null,null,null)
            cursor.whatIfNotNull {
                listuser.clear()
                while (it.moveToNext()){
                    val user = User(it.getString(0),it.getString(1),it.getString(2),it.getString(3),it.getString(4),it.getInt(5),it.getInt(6),it.getInt(7))
                    listuser.add(user)
                }
                runOnUiThread {
                    val adapter by lazy { UserAdapter() }
                    recycler.adapter = adapter.apply {
                        addItemList(listuser)
                    }
                    recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                }
                it.close()
            }
        }
    }

}
