package com.dakusuno.dakusunogua.view.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.base.DatabindingFragment
import com.dakusuno.dakusunogua.databinding.FragmentMainBinding
import com.dakusuno.dakusunogua.view.adapter.ItemAdapter
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : DatabindingFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = binding<FragmentMainBinding>(inflater,R.layout.fragment_main,container).apply {
            lifecycleOwner = this@MainFragment
            viewModel = getViewModel<MainViewModel>().apply {
                username.observe(viewLifecycleOwner, Observer {
                    viewModel = getViewModel<MainViewModel>().apply { fetchSearch(it) }
                })
            }
            adapter = ItemAdapter()
        }.root
        return binding
    }


}