package com.dakusuno.dakusunogua.view.ui.user

import android.os.Bundle
import android.view.View
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.base.BaseFragment
import com.dakusuno.dakusunogua.databinding.FragmentFollowingBinding
import com.dakusuno.dakusunogua.view.adapter.FollowerAdapter
import com.dakusuno.dakusunogua.view.adapter.FollowingAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FollowingFragment : BaseFragment(){
    override fun layoutId(): Int {
        return R.layout.fragment_following
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel by sharedViewModel<UserViewModel>()
        super.onViewCreated(view, savedInstanceState)
        FragmentFollowingBinding.bind(view).apply {
            lifecycleOwner = this@FollowingFragment
            this.viewModel = viewModel
            adapter = FollowingAdapter()
        }.root

    }
}