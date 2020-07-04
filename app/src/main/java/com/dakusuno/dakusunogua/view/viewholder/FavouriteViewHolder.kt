package com.dakusuno.dakusunogua.view.viewholder

import com.dakusuno.dakusunogua.databinding.ItemFavouriteBinding
import com.dakusuno.dakusunogua.model.User


import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.view.ui.user.UserViewModel
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

class FavouriteViewHolder(view:View,val viewModel: UserViewModel) : BaseViewHolder(view) {
    private lateinit var data: User
    private val binding: ItemFavouriteBinding by bindings(
        view
    )

    override fun bindData(data: Any) {
        if(data is User){
            this.data = data
            drawUI()
        }
    }
    fun drawUI(){
        binding.apply {
            user = data
            delete.setOnClickListener {
                viewModel.deleteUser(data)
            }
            executePendingBindings()
        }
    }


    override fun onClick(v: View) {
        val item = Item(data.login,data.avatar_url)
        val bundle = bundleOf("item" to item)
        v.findNavController().navigate(R.id.action_favouriteFragment_to_userFragment,bundle)
    }

    override fun onLongClick(v: View?): Boolean {
        return false
    }
}