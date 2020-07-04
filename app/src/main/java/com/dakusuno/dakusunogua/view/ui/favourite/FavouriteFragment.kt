package com.dakusuno.dakusunogua.view.ui.favourite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dakusuno.dakusunogua.R
import com.dakusuno.dakusunogua.base.BaseFragment
import com.dakusuno.dakusunogua.databinding.FragmentFavouriteBinding
import com.dakusuno.dakusunogua.model.User
import com.dakusuno.dakusunogua.view.adapter.FavouriteAdapter
import com.dakusuno.dakusunogua.view.ui.user.UserViewModel
import kotlinx.android.synthetic.main.item_favourite.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavouriteFragment : BaseFragment() {
    var user:MutableLiveData<User> = MutableLiveData()
    override fun layoutId(): Int {
        return R.layout.fragment_favourite
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel by sharedViewModel<UserViewModel>()

        super.onViewCreated(view, savedInstanceState)
        FragmentFavouriteBinding.bind(view).apply {
            lifecycleOwner = this@FavouriteFragment
            this.viewModel = viewModel
            adapter = FavouriteAdapter(viewModel).apply {
            }
        }.root
    }
}