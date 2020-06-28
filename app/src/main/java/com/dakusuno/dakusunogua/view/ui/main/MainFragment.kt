package com.dakusuno.dakusunogua.view.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.base.BaseFragment
import com.dakusuno.dakusunogua.databinding.FragmentMainBinding
import com.dakusuno.dakusunogua.view.adapter.ItemAdapter
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : BaseFragment(){
    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentMainBinding.bind(view).apply {
            lifecycleOwner = this@MainFragment
            viewModel = getViewModel<MainViewModel>().apply {
                username.observe(viewLifecycleOwner, Observer {
                    viewModel = getViewModel<MainViewModel>().apply { fetchSearch(it)}
                })
            }
            adapter = ItemAdapter()
        }.root
    }


}