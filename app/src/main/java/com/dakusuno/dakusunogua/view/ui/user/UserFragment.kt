package com.dakusuno.dakusunogua.view.ui.user

import android.os.Bundle
import android.view.View
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.base.BaseFragment
import com.dakusuno.dakusunogua.databinding.FragmentUserBinding
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.view.adapter.UserPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class UserFragment : BaseFragment(){
    override fun layoutId(): Int {
        return R.layout.fragment_user
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item: Item = arguments?.getParcelable("item")!!
        val viewModel by sharedViewModel<UserViewModel>()
        activity?.toolbar?.title = item.login
        FragmentUserBinding.bind(view).apply {
            lifecycleOwner = this@UserFragment
            this.viewModel =  viewModel.apply {
                getUser(item.login)
            }
            pager.adapter = UserPagerAdapter(this@UserFragment, item)
            TabLayoutMediator(tab_layout,pager){tab, position ->
                when(position){
                    0 -> tab.text = resources.getString(R.string.followers)
                    else -> tab.text = resources.getString(R.string.following)
                }
            }.attach()
        }.root
    }
}