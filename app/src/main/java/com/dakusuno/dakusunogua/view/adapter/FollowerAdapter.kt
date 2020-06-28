package com.dakusuno.dakusunogua.view.adapter

import android.view.View
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.view.viewholder.FollowerViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow


class FollowerAdapter : BaseAdapter(){

    init {
        addSection(arrayListOf<Item>())
    }
    fun addItemList(item:List<Item>){
        sections().first().run {
            clear()
            addAll(item)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_follower
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return FollowerViewHolder(view)
    }
}