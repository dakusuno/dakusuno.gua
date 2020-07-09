package com.dakusuno.favoritgua

import android.view.View
import com.dakusuno.favoritgua.model.User
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class UserAdapter(): BaseAdapter() {
    init {
        addSection(arrayListOf<User>())
    }
    fun addItemList(user:List<User>){
        sections().first().run {
            clear()
            addAll(user)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_user
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return UserViewHolder(view)
    }
}