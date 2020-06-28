package com.dakusuno.dakusunogua.view.ui.user

import android.os.Bundle
import android.util.Log
import android.view.View
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.base.BaseFragment
import com.dakusuno.dakusunogua.databinding.FragmentFollowerBinding
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.view.adapter.FollowerAdapter
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FollowerFragment : BaseFragment(){
    override fun layoutId(): Int {
        return R.layout.fragment_follower
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel by sharedViewModel<UserViewModel>()
        Timber.d("Follower Fragment")
        super.onViewCreated(view, savedInstanceState)
        FragmentFollowerBinding.bind(view).apply {
            lifecycleOwner = this@FollowerFragment
            this.viewModel = viewModel
            adapter = FollowerAdapter()
        }.root
    }

}