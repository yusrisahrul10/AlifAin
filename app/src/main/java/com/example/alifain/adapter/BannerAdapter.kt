package com.example.alifain.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.alifain.BannerFragment
import com.example.alifain.model.ImageSlideModel

class BannerAdapter(fragmentManager: FragmentManager,
                    private val banners: List<ImageSlideModel>) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(pos: Int): Fragment {
        return BannerFragment.newInstance(banners[pos].image)
    }

    override fun getCount(): Int = banners.size
}