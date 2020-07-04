package com.dakusuno.dakusunogua.view.adapter

import android.view.View
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.model.User
import com.dakusuno.dakusunogua.view.ui.user.UserViewModel
import com.dakusuno.dakusunogua.view.viewholder.FavouriteViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class FavouriteAdapter constructor(private val favouriteViewModel: UserViewModel) : BaseAdapter(){
    init {
        addSection(arrayListOf<User>())
    }
    fun addItemList(item:List<User>){
        sections().first().run {
            clear()
            addAll(item)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_favourite
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return FavouriteViewHolder(view,favouriteViewModel)
    }



}