package com.example.alifain.item

import android.support.v4.app.FragmentManager
import com.example.alifain.R
import com.example.alifain.adapter.BannerAdapter
import com.example.alifain.model.ImageSlideModel
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_carousel_banner.view.*

interface BannerListener {
    fun onSeeAllImageClick()
    fun onImageClick(image: ImageSlideModel)
}

class BannerCarouselItem(private val banners: List<ImageSlideModel>,
                         private val supportFragmentManager: FragmentManager,
                         private val listener: BannerListener) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val viewPagerAdapter = BannerAdapter (supportFragmentManager, banners)
        viewHolder.itemView.viewPagerBanner.adapter = viewPagerAdapter
        viewHolder.itemView.indicator.setViewPager(viewHolder.itemView.viewPagerBanner)
    }

    override fun getLayout(): Int = R.layout.item_carousel_banner
}