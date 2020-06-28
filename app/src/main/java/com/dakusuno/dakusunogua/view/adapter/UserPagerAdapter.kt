package com.dakusuno.dakusunogua.view.adapter

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dakusuno.dakusunogua.model.Item
import com.dakusuno.dakusunogua.view.ui.user.FollowerFragment
import com.dakusuno.dakusunogua.view.ui.user.FollowingFragment

class UserPagerAdapter(fragment: Fragment,val item: Item):FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        when (position){
            0 -> return FollowerFragment()
            else -> return FollowingFragment()
        }
    }

}