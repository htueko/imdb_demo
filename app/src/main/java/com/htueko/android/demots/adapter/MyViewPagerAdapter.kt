package com.htueko.android.demots.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.htueko.android.demots.ui.error.ErrorFragment
import com.htueko.android.demots.ui.nowplaying.NowPlayingFragment
import com.htueko.android.demots.ui.upcoming.UpComingFragment

class MyViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> NowPlayingFragment.newInstance()
            1 -> UpComingFragment.newInstance()
            else -> ErrorFragment.newInstance()
        }
    }

    override fun getItemCount(): Int {
        return FRAGMENT_COUNT
    }

    companion object {
        private const val FRAGMENT_COUNT = 2
    }
}