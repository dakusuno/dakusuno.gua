package com.dakusuno.dakusunogua.view.viewholder

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.databinding.ItemUserBinding
import com.dakusuno.dakusunogua.model.Item
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

class ItemViewHolder(view: View):BaseViewHolder(view){

    private lateinit var data: Item
    private val binding: ItemUserBinding by bindings(
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
            item = data
            executePendingBindings()
        }
    }

    override fun onClick(v: View) {
        val bundle = bundleOf("item" to data)
        v.findNavController().navigate(R.id.action_mainFragment_to_userFragment,bundle)
    }

    override fun onLongClick(v: View?): Boolean {
        return true
    }

}