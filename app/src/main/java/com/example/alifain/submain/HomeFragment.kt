package com.example.alifain.submain


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.alifain.R
import com.example.alifain.adapter.NewProductAdapter
import com.example.alifain.item.BannerCarouselItem
import com.example.alifain.item.BannerListener
import com.example.alifain.model.ImageSlideModel
import com.example.alifain.model.ProductModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), BannerListener {
    override fun onSeeAllImageClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onImageClick(image: ImageSlideModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var items: MutableList<ProductModel> = mutableListOf()

    private var groupAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val list = view.findViewById<RecyclerView>(R.id.new_product_list)
        initData()

        list.layoutManager = GridLayoutManager(context, 2)
        list.adapter = NewProductAdapter(context, items)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val images = listOf(
            ImageSlideModel(name = "Image 1",
                image = R.drawable.img_1),
            ImageSlideModel(name = "Image 2",
                image = R.drawable.img_2),
            ImageSlideModel(name = "Image 3",
                image = R.drawable.img_3)

        )

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
            isNestedScrollingEnabled = false
        }

        val bannerCarouselItem = BannerCarouselItem(images, childFragmentManager, this )
        groupAdapter.add(bannerCarouselItem)

    }

    private fun initData() {
        val name = resources.getStringArray(R.array.name_new_product)
        val image =  resources.obtainTypedArray(R.array.image_new_product)
        val price = resources.obtainTypedArray(R.array.price_new_product)

        items.clear()
        for (i in name.indices) {
            items.add(
                ProductModel(name[i],
                image.getResourceId(i, 0),
                price.getResourceId(i, 0))
            )
        }

        //Recycle the typed array
        image.recycle()
        price.recycle()
    }


}
