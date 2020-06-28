package com.dakusuno.dakusunogua.view.viewholder

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.databinding.ItemFollowerBinding
import com.dakusuno.dakusunogua.databinding.ItemUserBinding
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.view.ui.user.UserFragment
import com.dakusuno.dakusunogua.view.ui.user.UserViewModel
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FollowerViewHolder(view:View) : BaseViewHolder(view) {
    private lateinit var data: Item
    private val binding: ItemFollowerBinding by bindings(
        view
    )

    override fun bindData(data: Any) {
        if(data is Item){
            this.data = data
            drawUI()
        }
    }
    fun drawUI(){
        binding.apply {
            follower = data
            executePendingBindings()
        }
    }

    override fun onClick(v: View) {
        val bundle = bundleOf("item" to data)
        v.findNavController().navigate(R.id.action_userFragment_self,bundle)
    }

    override fun onLongClick(v: View?): Boolean {
        return false
    }
}