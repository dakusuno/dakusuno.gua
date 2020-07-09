package com.dakusuno.favoritgua

import android.view.View
import com.bumptech.glide.Glide
import com.dakusuno.favoritgua.model.User
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(val view: View): BaseViewHolder(view){

    private lateinit var user: User

    override fun bindData(data: Any) {
        if(data is User){
            user = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            item_username.text = user.login
            Glide.with(view.context)
                .load(user.avatar_url)
                .into(findViewById(R.id.item_avatar))

        }
    }

    override fun onClick(v: View?) {
//        TODO("Not yet implemented")
    }


    override fun onLongClick(v: View?): Boolean {
        return false
    }

}