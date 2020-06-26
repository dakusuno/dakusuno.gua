package com.dakusuno.dakusunogua.view.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.base.DatabindingFragment
import com.dakusuno.dakusunogua.databinding.FragmentUserBinding
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.model.User
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserFragment : DatabindingFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val item: Item = arguments?.getParcelable("item")!!
        val user: User? = getViewModel<UserViewModel>().user.value
        Log.d("item",item.toString())
        Log.d("user",user.toString())
        return binding<FragmentUserBinding>(inflater,R.layout.fragment_user,container).apply {
            lifecycleOwner = this@UserFragment
            this.user = user
        }.root

    }
}